import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { User } from '../interface/user';
import { CreateUser } from '../interface/createUser';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/usuarios';
  private usersSubject = new BehaviorSubject<User[]>([]);

  constructor(private http: HttpClient) {}

  fetchUsers(): Observable<User[]> {
    return this.http
      .get<User[]>(this.apiUrl)
      .pipe(tap((users) => this.usersSubject.next(users)));
  }

  getUsersObservable(): Observable<User[]> {
    return this.usersSubject.asObservable();
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }

  getNextId(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/next-id`);
  }

  createUser(createUser: CreateUser): Observable<User> {
    return this.http
      .post<User>(this.apiUrl, createUser)
      .pipe(tap(() => this.fetchUsers().subscribe()));
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.http
      .put<User>(`${this.apiUrl}/${id}`, user)
      .pipe(tap(() => this.fetchUsers().subscribe()));
  }

  deleteUser(id: number): Observable<void> {
    return this.http
      .delete<void>(`${this.apiUrl}/${id}`)
      .pipe(tap(() => this.fetchUsers().subscribe()));
  }
}
