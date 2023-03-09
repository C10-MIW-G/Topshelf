import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAddGroceryProductComponent } from './modal-add-grocery-product.component';

describe('ModalAddGroceryProductComponent', () => {
  let component: ModalAddGroceryProductComponent;
  let fixture: ComponentFixture<ModalAddGroceryProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalAddGroceryProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalAddGroceryProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
