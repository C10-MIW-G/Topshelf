import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionBarPantryDetailComponent } from './action-bar-pantry-detail.component';

describe('ActionBarPantryDetailComponent', () => {
  let component: ActionBarPantryDetailComponent;
  let fixture: ComponentFixture<ActionBarPantryDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActionBarPantryDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActionBarPantryDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
