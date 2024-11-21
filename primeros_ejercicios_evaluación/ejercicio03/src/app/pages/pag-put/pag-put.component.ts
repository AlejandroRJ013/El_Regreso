import { Component, ViewChild, ElementRef } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { User } from '../../interface/user';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-pag-put',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pag-put.component.html',
  styleUrls: ['./pag-put.component.css', '../../app.component.css'],
})
export class PagPutComponent {
  @ViewChild('userIdInput', { static: true })
  userIdInput!: ElementRef<HTMLInputElement>;
  @ViewChild('nombreInput', { static: true })
  nombreInput!: ElementRef<HTMLInputElement>;
  @ViewChild('emailInput', { static: true })
  emailInput!: ElementRef<HTMLInputElement>;

  userVisible: User = {
    id: 0,
    nombre: 'Ejemplo',
    email: 'ejemplo@example.com',
  };

  usuarioActualizado: User = { nombre: '', email: '' };

  constructor(
    private router: Router,
    private rute: ActivatedRoute,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.rute.params.subscribe((params) => {
      if (Object.keys(params).length > 0) {
        const id = params['id'];
        this.buscarUsuario(id);
      }
    });
  }

  recogerInputBuscar() {
    const id = parseInt(this.userIdInput.nativeElement.value);

    this.buscarUsuario(id);
  }

  buscarUsuario(id: number) {
    if (!isNaN(id)) {
      this.userService.getUserById(id).subscribe({
        next: (user) => {
          this.userVisible = user;
        },
        error: (error) => {
          alert(error);
        },
      });
    } else {
      alert('Por favor, ingresa un ID válido.');
    }
  }

  onKeyDown(event: KeyboardEvent): void {
    if (event.key === 'Enter') {
      this.recogerInputBuscar();
    }
  }

  actualizarUsuario() {
    this.usuarioActualizado.nombre = this.nombreInput.nativeElement.value;
    this.usuarioActualizado.email = this.emailInput.nativeElement.value;

    this.userService
      .updateUser(this.userVisible.id!, this.usuarioActualizado)
      .subscribe({
        next: () => {
          alert('Usuario actualizado con éxito.');
        },
        error: (error) => {
          alert(error);
        },
      });

    this.nombreInput.nativeElement.value = '';
    this.emailInput.nativeElement.value = '';

    this.router.navigate(['/inicio']);
  }
}
