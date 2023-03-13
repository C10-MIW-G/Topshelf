import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ModalUserComponent } from '../modal-user/modal-user.component';
import { User } from '../user/user';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-action-bar-user',
  templateUrl: './action-bar-user.component.html',
  styleUrls: ['./action-bar-user.component.css'],
})
export class ActionBarUserComponent {
  public isAdmin = false;
  public user!: User;
  public namePantry!: string;
  public pantryId!: number;
  public emailUser!: string;
  public openNewModal?: boolean;
  public errorMessage?: string;
  public isLoading?: boolean;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private matDialog: MatDialog,
    private toastr: ToastrService
  ) {}

  onOpenDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      isSubmitted: true,
    };

    const dialogRef = this.matDialog.open(ModalUserComponent, dialogConfig);

    dialogRef.afterClosed().subscribe((data) => {
      this.saveUserToPantry(data);
    });
  }

  public saveUserToPantry(data: any) {
    if (data.isSubmitted) {
      this.isLoading = true;
      const emailUser = String(data.inviteUserToPantryForm);
      this.userService
        .inviteUserToPantry(emailUser, this.getPantryId())
        .subscribe({
          complete: () => {
            if (data.openNewModal == true) {
              this.toastr.success('User invited!', 'Success!', {
                positionClass: 'toast-top-center',
              });
              this.openNewModal = true;
              window.location.reload();
            }
          },
          error: (_error: HttpErrorResponse) => {
            this.isLoading = false;
            if (_error.status == 403) {
              this.toastr.error(
                'User is already part of the pantry',
                'User invitation failed!',
                {
                  positionClass: 'toast-top-center',
                }
              );
            }
            if (_error.status == 400) {
              this.toastr.error(
                "User doesn't have an account",
                'User invitation failed!',
                {
                  positionClass: 'toast-top-center',
                }
              );
            }
          },
        });
    } else {
      window.location.reload();
    }
  }

  public getCurrentPantryAdmin(pantryId: number): any {
    this.userService.checkIfUserIsPantryAdmin(pantryId).subscribe(
      (response: User) => {
        this.user = response;
        this.isAdmin = true;
        if (response == null) {
          this.isAdmin = false;
        }
      },
      (_error: HttpErrorResponse) => {
        if (_error.status == 400) {
          this.isAdmin = false;
          this.errorMessage = 'Current user is not an admin of this pantry';
        }
      }
    );
  }

  public getPantryName() {
    this.route.queryParams.subscribe((params) => {
      this.namePantry = params['name'];
    });
  }

  public getPantryId(): number {
    this.route.parent?.params.subscribe((params) => {
      const response = params['pantryId'];
      this.pantryId = parseInt(response.split(';')[0], 10);
    });
    return this.pantryId;
  }
}
