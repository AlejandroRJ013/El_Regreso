import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { User } from '../../interface/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pag-post',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pag-post.component.html',
  styleUrls: ['./pag-post.component.css', '../../app.component.css'],
})
export class PagPostComponent implements OnInit {
  @ViewChild('nombreInput', { static: true })
  nombreInput!: ElementRef<HTMLInputElement>;
  @ViewChild('emailInput', { static: true })
  emailInput!: ElementRef<HTMLInputElement>;

  nextId: number | null = null;
  usuarioNuevo: User = { nombre: '', email: '' };

  constructor(private router: Router, private userService: UserService) {}

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

    this.userService.createUser(this.usuarioNuevo).subscribe({
      next: (usuarioCreado) => {
        alert('Usuario creado: ' + usuarioCreado.nombre);
        console.log('Usuario creado:', usuarioCreado.nombre);
      },
      error: (err) => {
        alert('Error al crear el usuario: \n' + err);
        console.error('Error al crear el usuario:', err);
      },
    });

    this.nombreInput.nativeElement.value = '';
    this.emailInput.nativeElement.value = '';

    this.router.navigate(['/inicio']);
  }
}
