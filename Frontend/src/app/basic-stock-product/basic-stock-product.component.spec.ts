import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BasicStockProductComponent } from './basic-stock-product.component';

describe('BasicstockproductComponent', () => {
  let component: BasicStockProductComponent;
  let fixture: ComponentFixture<BasicStockProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BasicStockProductComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(BasicStockProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
