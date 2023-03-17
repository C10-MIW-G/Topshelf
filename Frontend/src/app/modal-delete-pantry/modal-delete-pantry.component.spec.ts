import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalDeletePantryComponent } from './modal-delete-pantry.component';

describe('ModalDeletePantryComponent', () => {
  let component: ModalDeletePantryComponent;
  let fixture: ComponentFixture<ModalDeletePantryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalDeletePantryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalDeletePantryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
