import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionBarStockProductComponent } from './action-bar-stock-product.component';

describe('ActionBarStockProductComponent', () => {
  let component: ActionBarStockProductComponent;
  let fixture: ComponentFixture<ActionBarStockProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActionBarStockProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActionBarStockProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
