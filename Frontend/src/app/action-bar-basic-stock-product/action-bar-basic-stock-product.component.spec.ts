import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionBarBasicStockProductComponent } from './action-bar-basic-stock-product.component';

describe('ActionBarBasicStockProductComponent', () => {
  let component: ActionBarBasicStockProductComponent;
  let fixture: ComponentFixture<ActionBarBasicStockProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActionBarBasicStockProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActionBarBasicStockProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
