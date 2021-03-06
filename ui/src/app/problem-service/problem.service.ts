import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Problem } from '../../model/problem';

@Injectable({ providedIn: 'root' })
export class ProblemService {

  private host       = 'http://localhost:9000';
  private problemUrl = `http://localhost:9000/kpt/problem`;

  constructor(
    private http: HttpClient,
  ) { }

  getProblems(): Observable<Problem[]> {
    return this.http.get<Problem[]>(this.problemUrl)
  }
}
