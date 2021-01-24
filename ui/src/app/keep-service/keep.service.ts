import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import { Keep } from '../../model/keep';

@Injectable({ providedIn: 'root' })
export class KeepService {

  private host    = 'http://localhost:9000';
  private keepUrl = `${this.host}/kpt/keep`;

  constructor(
    private http: HttpClient,
  ) { }

  getKeeps(): Observable<Keep[]> {
    return this.http.get<Keep[]>(this.keepUrl)
  }
}
