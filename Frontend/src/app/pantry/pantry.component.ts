import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Pantry } from './pantry';
import { PantryService } from './pantry.service';

@Component({
    selector: 'app-pantry',
    templateUrl: './pantry.component.html',
    styleUrls: ['./pantry.component.css']
})
export class PantryComponent implements OnInit {
    public pantries?: Pantry[] = [];
    
    constructor(private pantryService: PantryService) { }

    ngOnInit() {
        this.getPantries();
    }

    public getPantries(): void {
        this.pantryService.getPantries().subscribe(
            (response: Pantry[]) => {
                this.pantries = response;
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
            }
        );
    }
}