import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModaladdbasicstockComponent } from './modaladdbasicstock.component';

describe('ModaladdbasicstockComponent', () => {
  let component: ModaladdbasicstockComponent;
  let fixture: ComponentFixture<ModaladdbasicstockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModaladdbasicstockComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModaladdbasicstockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
