import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroceryProductComponent } from './grocery-product.component';

describe('GroceryProductComponent', () => {
  let component: GroceryProductComponent;
  let fixture: ComponentFixture<GroceryProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroceryProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GroceryProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
