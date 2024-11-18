import { Routes } from '@angular/router';
import { InicioComponent } from './pages/inicio/inicio.component';
import { PagGetComponent } from './pages/pag-get/pag-get.component';
import { PagPostComponent } from './pages/pag-post/pag-post.component';
import { PagPutComponent } from './pages/pag-put/pag-put.component';
import { PagDelComponent } from './pages/pag-del/pag-del.component';
import { PagSegDelComponent } from './pages/pag-seg-del/pag-seg-del.component';
import { PagNotFoundComponent } from './pages/pag-not-found/pag-not-found.component';

export const routes: Routes = [
  { path: '', component: InicioComponent },
  { path: 'buscar-usuario', component: PagGetComponent },
  { path: 'crear-usuario', component: PagPostComponent },
  { path: 'actualizar-usuario', component: PagPutComponent },
  { path: 'eliminar-usuario', component: PagDelComponent },
  { path: 'confirmar-eliminacion-usuario', component: PagSegDelComponent },
  { path: 'inicio', redirectTo: '', pathMatch: 'full' },
  { path: '**', component: PagNotFoundComponent },
];
