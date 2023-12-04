package com.nagarro.library_management.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class LibraryManagementService {
	
	public List<String> getBook(String link) {
		List<String> book = new ArrayList<>();
		try {
			URL url = new URL(link);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int responsecode = con.getResponseCode();
			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				Scanner sc = new Scanner(url.openStream());
				String json = "";
				while (sc.hasNext()) {
					json += sc.nextLine();
				}
				sc.close();
				JSONParser parse = new JSONParser();
				JSONObject jobj = (JSONObject) parse.parse(json);
				Long code = (Long) jobj.get("code");
				String name = (String) jobj.get("name");
				String author = (String) jobj.get("author");
				String date = (String) jobj.get("date");
				book.add(code.toString());
				book.add(name);
				book.add(author);
				book.add(date);
				book.add(link);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public void deleteBook(String link) {
		try {
			URL url = new URL(link);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
			System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String finish(HttpURLConnection httpConn) throws IOException {

		String response = "";

		int status = httpConn.getResponseCode();
		if (status == HttpURLConnection.HTTP_OK || status == HttpURLConnection.HTTP_CREATED) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				response += line;
			}
			reader.close();
			httpConn.disconnect();
		} else {
			throw new IOException("Server returned non-OK status: " + status);
		}

		return response;
	}

	public void editBooks(String editLink, String name, String author) {
		try {
			URL url = new URL(editLink);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PATCH");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			String json = "{\"name\": \""+name+"\", \"author\": \""+author+"\"}";
			try (OutputStream os = con.getOutputStream()) {
				byte[] input = json.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> authors() {
		List<String> authors = new ArrayList<>();
		try {
			URL url = new URL("http://localhost:8080/authors");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int responsecode = con.getResponseCode();
			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				Scanner sc = new Scanner(url.openStream());
				String json = "";
				while (sc.hasNext()) {
					json += sc.nextLine();
				}
				sc.close();
				JSONParser parse = new JSONParser();
				JSONObject jobj = (JSONObject) parse.parse(json);
				JSONObject embeded = (JSONObject) jobj.get("_embedded");
				JSONArray arr = (JSONArray) embeded.get("authors");
				for (Object obj : arr) {
					String name = (String) ((JSONObject) obj).get("name");
					authors.add(name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authors;
	}

	public List<List<String>> books() {
		List<List<String>> books = new ArrayList<>();
		try {
			URL url = new URL("http://localhost:8080/books");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int responsecode = con.getResponseCode();
			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				Scanner sc = new Scanner(url.openStream());
				String json = "";
				while (sc.hasNext()) {
					json += sc.nextLine();
				}
				sc.close();
				JSONParser parse = new JSONParser();
				JSONObject jobj = (JSONObject) parse.parse(json);
				JSONObject embeded = (JSONObject) jobj.get("_embedded");
				JSONArray arr = (JSONArray) embeded.get("books");
				for (Object obj : arr) {
					List<String> book = new ArrayList<>();
					String date = (String) ((JSONObject) obj).get("date");
					Long code = (Long) ((JSONObject) obj).get("code");
					String author = (String) ((JSONObject) obj).get("author");
					String name = (String) ((JSONObject) obj).get("name");
					book.add(code.toString());
					book.add(name);
					book.add(author);
					book.add(date);

					JSONObject links = (JSONObject) ((JSONObject) obj).get("_links");
					JSONObject link = (JSONObject) links.get("self");
					String editLink = (String) link.get("href");
					book.add(editLink);

					books.add(book);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	public boolean addbook(String code, String name, String author, String date) {
		try {
			URL url = new URL("http://localhost:8080/books");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			String jsonInputString = "{\"code\": \"" + code + "\", \"name\": \"" + name + "\", \"author\": \"" + author
					+ "\", \"date\": \"" + date + "\"}";
			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			int responsecode = con.getResponseCode();
			if (responsecode != 201) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
