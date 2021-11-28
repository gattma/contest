import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { SearchResult } from '../models/SearchResult';

const MOCKDATA: Array<SearchResult> = [
  {title: "Title 1", subject: "Subject 1"},
  {title: "Title 2", subject: "Subject 2"},
  {title: "Title 3", subject: "Subject 3"},
  {title: "Title 4", subject: "Subject 4"},
];

@Injectable({
  providedIn: 'root'
})
export class SolrSearchServiceService {

  constructor(private http: HttpClient) { }

  search(keywords: string): Observable<Array<SearchResult>> {
    console.log("Search with keywords: ", keywords);
    return this.http.get<Array<SearchResult>>("https://solr-frontend-facade-contest.apps.play.gepaplexx.com/search?text=" + keywords);
  }

  searchMock(keywords: string): Observable<Array<SearchResult>> {
    console.log("Search with keywords: ", keywords);
    return of(MOCKDATA);
  }
}
