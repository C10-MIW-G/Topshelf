import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalStockProductComponent } from './modal-stock-product.component';

describe('ModalStockProductComponent', () => {
  let component: ModalStockProductComponent;
  let fixture: ComponentFixture<ModalStockProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalStockProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalStockProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
