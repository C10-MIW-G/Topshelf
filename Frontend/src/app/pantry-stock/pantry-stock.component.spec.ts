import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PantryStockComponent } from './pantry-stock.component';

describe('PantryStockComponent', () => {
  let component: PantryStockComponent;
  let fixture: ComponentFixture<PantryStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PantryStockComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PantryStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
