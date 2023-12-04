package search;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductSearch {

	public static void search() {
		String color, size, gender, sortOrder = null;

		while (true) {
			color = Inputs.getColorInput();
			size = Inputs.getSizeInput();
			gender = Inputs.getGenderInput();
			sortOrder = Inputs.getSortingOrder();

			try {
				String hql = "FROM Product WHERE color='" + color + "' AND size='" + size + "' AND gender='" + gender
						+ "'" + sortOrder;
				Session session = Main.sf.openSession();
				Transaction tx = session.beginTransaction();
				@SuppressWarnings("unchecked")
				List<Product> result = session.createQuery(hql).list();
				if (!result.isEmpty()) {
					for (Product s : result) {
						System.out.println(s.getDetails());
					}
				} else {
					System.out.println("\nNo matching T-Shirt found, try changing colour or size");
					System.out.print("\nOr, you can try changing folder location of csv files by pressing 'y': ");
					if(Inputs.yListner()) {
						Inputs.fileLoader();
					}
				}

				tx.commit();
				session.close();

			} catch (Exception e) {
				System.out.println(e);
			}

			System.out.println("\nDo you want to exit? ");
			System.out.print("\nPress y for yes: ");
			if (Inputs.yListner()) {
				break;
			}
		}
	}
}
