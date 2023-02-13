import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModaladdpantryComponent } from './modaladdpantry.component';

describe('ModaladdpantryComponent', () => {
  let component: ModaladdpantryComponent;
  let fixture: ComponentFixture<ModaladdpantryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModaladdpantryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModaladdpantryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
