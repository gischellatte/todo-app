import { render, screen } from '@testing-library/react';
import TodoList from '../TodoList';
import { describe, it, expect, vi, beforeEach } from 'vitest';

describe('TodoList', () => {
  const mockTasks = [
    { id: 1, taskName: 'Task 1', deadline: '05 Jan 2027' },
    { id: 2, taskName: 'Task 2', deadline: '15 Feb 2027' },
    { id: 3, taskName: 'Task 3', deadline: '18 Feb 2027' },
  ];

  beforeEach(() => {
    global.fetch = vi.fn((url) => {
      if (url.endsWith('/categories')) {
        return Promise.resolve({
          ok: true,
          json: () => Promise.resolve([]),
        });
      }

      if (url.endsWith('/todos')) {
        return Promise.resolve({
          ok: true,
          json: () => Promise.resolve(mockTasks),
        });
      }

      if (url.endsWith('/todos/archived')) {
        return Promise.resolve({
          ok: true,
          json: () => Promise.resolve([]),
        });
      }
    });
  });

  it('renders todos', async () => {
    render(<TodoList />);

    expect(await screen.findByDisplayValue('Task 1')).toBeInTheDocument();
    expect(await screen.findByText(/05 Jan 2027/)).toBeInTheDocument();

    expect(await screen.findByDisplayValue('Task 2')).toBeInTheDocument();
    expect(await screen.findByText(/15 Feb 2027/)).toBeInTheDocument();

    expect(await screen.findByDisplayValue('Task 3')).toBeInTheDocument();
    expect(await screen.findByText(/18 Feb 2027/)).toBeInTheDocument();
  });
});


