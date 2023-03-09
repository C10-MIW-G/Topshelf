import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionBarGroceryProductComponent } from './action-bar-grocery-product.component';

describe('ActionBarGroceryProductComponent', () => {
  let component: ActionBarGroceryProductComponent;
  let fixture: ComponentFixture<ActionBarGroceryProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActionBarGroceryProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActionBarGroceryProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
