import { TestBed } from '@angular/core/testing';

import { SolrSearchServiceService } from './solr-search-service.service';

describe('SolrSearchServiceService', () => {
  let service: SolrSearchServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SolrSearchServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
