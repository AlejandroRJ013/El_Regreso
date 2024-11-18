import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css',
})
export class InicioComponent implements OnInit, OnDestroy {
  users: any[] = [];
  private usersSubscription!: Subscription;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.usersSubscription = this.userService
      .getUsersObservable()
      .subscribe((data) => {
        this.users = data;
      });

    this.userService.fetchUsers().subscribe();
  }

  ngOnDestroy(): void {
    // Cancelar la suscripción para evitar fugas de memoria
    this.usersSubscription.unsubscribe();
  }
}
