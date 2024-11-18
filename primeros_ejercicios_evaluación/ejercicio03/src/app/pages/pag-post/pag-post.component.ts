import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CreateUser } from '../../interface/createUser';

@Component({
  selector: 'app-pag-post',
  standalone: true,
  imports: [],
  templateUrl: './pag-post.component.html',
  styleUrl: './pag-post.component.css',
})
export class PagPostComponent implements OnInit {
  nextId: number | null = null;

  @ViewChild('nombreInput', { static: true })
  nombreInput!: ElementRef<HTMLInputElement>;
  @ViewChild('emailInput', { static: true })
  emailInput!: ElementRef<HTMLInputElement>;

  usuarioNuevo: CreateUser = { nombre: '', email: '' };

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getNextId().subscribe({
      next: (id) => {
        this.nextId = id;
      },
      error: (err) => {
        console.error('Error al obtener el próximo ID:', err);
      },
    });
  }

  crearUsuario() {
    this.usuarioNuevo.nombre = this.nombreInput.nativeElement.value;
    this.usuarioNuevo.email = this.emailInput.nativeElement.value;
    this.userService.createUser(this.usuarioNuevo);
  }
}
