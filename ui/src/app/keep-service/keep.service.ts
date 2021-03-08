import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Keep } from '../../model/keep';

@Injectable({ providedIn: 'root' })
export class KeepService {

  private host       = 'http://localhost:9000';
  private getAllKeepUrl = `${this.host}/kpt/keep`;
  private addKeepUrl   = `${this.host}/kpt/keep/post`;

  constructor(
    private http: HttpClient,
  ) { }

  getKeeps(): Observable<Keep[]> {
    return this.http.get<Keep[]>(this.getAllKeepUrl)
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  add(keep: Keep): Observable<Keep> {
    return this.http.post<Keep>(this.addKeepUrl, keep, this.httpOptions).pipe(
      tap((newData: Keep) => console.log(`added hero w/ id=${newData.id}`)),
      // catchError(this.handleError<Keep>('addHero'))
    )
  }
}
