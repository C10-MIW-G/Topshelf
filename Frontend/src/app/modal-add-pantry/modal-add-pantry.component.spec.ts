import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAddPantryComponent } from './modal-add-pantry.component';

describe('ModaladdpantryComponent', () => {
  let component: ModalAddPantryComponent;
  let fixture: ComponentFixture<ModalAddPantryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalAddPantryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalAddPantryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
