import { Component, ViewChild, ElementRef } from '@angular/core';
import { User } from '../../interface/user';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-pag-get',
  standalone: true,
  imports: [],
  templateUrl: './pag-get.component.html',
  styleUrl: './pag-get.component.css',
})
export class PagGetComponent {
  @ViewChild('userIdInput', { static: true })
  userIdInput!: ElementRef<HTMLInputElement>;

  userVisible: User = {
    id: 0,
    nombre: 'Ejemplo',
    email: 'ejemplo@example.com',
  };

  constructor(private userService: UserService) {}

  buscarUsuario() {
    const id = parseInt(this.userIdInput.nativeElement.value);

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
      this.buscarUsuario();
    }
  }
}
