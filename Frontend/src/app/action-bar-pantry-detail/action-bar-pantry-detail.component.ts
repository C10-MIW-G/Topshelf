import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Pantry } from '../pantry/pantry';
import { PantryService } from '../pantry/pantry.service';

@Component({
  selector: 'app-action-bar-pantry-detail',
  templateUrl: './action-bar-pantry-detail.component.html',
  styleUrls: ['./action-bar-pantry-detail.component.css']
})
export class ActionBarPantryDetailComponent {
  public pantryId!: number;
  public pantry?: Pantry;
  public errorMessage?: string;

  constructor(
    private pantryService: PantryService,
    private route: ActivatedRoute,
    private router: Router,
    private matDialog: MatDialog,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.getPantryId();
  }

  public remove() {
    this.pantryService
      .deletePantry(this.pantryId)
      .subscribe(() => {
        this.router.navigate(['/pantry']);
        this.toastr.success('Pantry has been deleted!', 'Success!', {
          positionClass: 'toast-top-center',
        })
      }),
      (_error: HttpErrorResponse) => {
        this.errorMessage = "Pantry was not deleted, please try again"
      };
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }

}
