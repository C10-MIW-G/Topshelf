import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SameNameDialogComponent } from './same-name-dialog.component';

describe('SameNameDialogComponent', () => {
  let component: SameNameDialogComponent;
  let fixture: ComponentFixture<SameNameDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SameNameDialogComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SameNameDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
