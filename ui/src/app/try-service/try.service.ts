import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Try } from '../../model/try';

@Injectable({ providedIn: 'root' })
export class TryService {

  private host    = 'http://localhost:9000';
  private tryUrl = `${this.host}/kpt/try`;

  constructor(
    private http: HttpClient,
  ) { }

  getTry(): Observable<Try[]> {
    return this.http.get<Try[]>(this.tryUrl)
  }
}
