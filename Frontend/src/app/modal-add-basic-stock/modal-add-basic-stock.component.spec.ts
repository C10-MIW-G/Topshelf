import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAddBasicStockComponent } from './modal-add-basic-stock.component';

describe('ModalAddBasicStockComponent', () => {
  let component: ModalAddBasicStockComponent;
  let fixture: ComponentFixture<ModalAddBasicStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalAddBasicStockComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalAddBasicStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
