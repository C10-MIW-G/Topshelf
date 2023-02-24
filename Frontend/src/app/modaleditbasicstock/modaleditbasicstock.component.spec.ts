import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModaleditbasicstockComponent } from './modaleditbasicstock.component';

describe('ModaleditbasicstockComponent', () => {
  let component: ModaleditbasicstockComponent;
  let fixture: ComponentFixture<ModaleditbasicstockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModaleditbasicstockComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModaleditbasicstockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
