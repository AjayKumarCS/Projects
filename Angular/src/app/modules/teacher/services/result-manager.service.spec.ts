import { TestBed } from '@angular/core/testing';

import { ResultManagerService } from './result-manager.service';

describe('ResultManagerService', () => {
  let service: ResultManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResultManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
