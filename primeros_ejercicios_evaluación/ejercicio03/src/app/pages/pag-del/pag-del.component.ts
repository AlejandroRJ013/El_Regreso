import { Component, ViewChild, ElementRef } from '@angular/core';
import { OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { User } from '../../interface/user';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pag-del',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pag-del.component.html',
  styleUrls: ['./pag-del.component.css', '../../app.component.css'],
})
export class PagDelComponent implements OnInit {
  @ViewChild('userIdInput', { static: true })
  userIdInput!: ElementRef<HTMLInputElement>;
  hayUsuario: boolean = false;
  userVisible: User = {
    id: 0,
    nombre: 'Ejemplo',
    email: 'ejemplo@example.com',
  };

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
          this.hayUsuario = true;
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

  eliminarUsuario() {
    this.userService.deleteUser(this.userVisible.id!).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (error) => {
        alert(error);
      },
    });
  }
}
