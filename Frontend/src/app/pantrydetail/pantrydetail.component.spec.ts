import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PantrydetailComponent } from './pantrydetail.component';

describe('PantrydetailComponent', () => {
  let component: PantrydetailComponent;
  let fixture: ComponentFixture<PantrydetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PantrydetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PantrydetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
