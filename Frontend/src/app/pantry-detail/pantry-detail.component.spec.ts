import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PantryDetailComponent } from './pantry-detail.component';

describe('PantryDetailComponent', () => {
  let component: PantryDetailComponent;
  let fixture: ComponentFixture<PantryDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PantryDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PantryDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
