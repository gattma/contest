import { Component } from '@angular/core';
import { SearchResult } from './models/SearchResult';
import { SolrSearchServiceService } from './services/solr-search-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  displayedColumns: string[] = ['index', 'subject', 'title'];

  title = 'contest-frontend';
  searchResults: Array<SearchResult>;

  constructor(private searchService: SolrSearchServiceService) {}

  search(event: any) {
    console.log("searching with ", event.target.value);
    this.searchService.search(event.target.value).subscribe((res: Array<SearchResult>) => {
      console.log("Found: ", res);
      this.searchResults = res;
    });
  }

}
