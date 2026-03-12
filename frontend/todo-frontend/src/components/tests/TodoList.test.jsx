import { render, screen } from '@testing-library/react';
import TodoList from '../TodoList';
import { describe, it, expect, vi, beforeEach } from 'vitest';
import userEvent from '@testing-library/user-event';

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

    // Just check one task at a time — simplest possible pattern
    expect(await screen.findByDisplayValue('Task 1')).toBeInTheDocument();
    expect(await screen.findByText(/05 Jan 2027/)).toBeInTheDocument();

    expect(await screen.findByDisplayValue('Task 2')).toBeInTheDocument();
    expect(await screen.findByText(/15 Feb 2027/)).toBeInTheDocument();

    expect(await screen.findByDisplayValue('Task 3')).toBeInTheDocument();
    expect(await screen.findByText(/18 Feb 2027/)).toBeInTheDocument();
  });


  it('Should show a new category after user clicks on the button', async () => {

    /*state depends on the server -> must use fetch when:
    1. Obtaining data from a server (GET) 
    2. Sends data to the server (POST/PUT/DELETE)
    3. Your state depends on the backend result
    */
    global.fetch = vi.fn (() => 
      Promise.resolve({
        json: () => Promise.resolve({id: 99, categoryName: "react"})
      }) )

    const user = userEvent.setup();
    render(<TodoList/>);
    const addCategBtn = screen.getByRole('button', { name: /Add Category/i });
    await user.click (addCategBtn);
    const addedCateg = await screen.findByText(/react/i);
    
    const selectElement = screen.getByRole('combobox'); 
    console.log(selectElement.innerHTML); 
    expect(addedCateg).toBeInTheDocument();
  });

it('It must duplicate a task', async () => {  
  const user = userEvent.setup();

  global.fetch = vi.fn((url, options) => {
    if (url.includes("/categories")) {
      return Promise.resolve({
        ok: true,
        json: () => Promise.resolve([])
      });
    }


    if(url.endsWith("/todos") && (!options || options.method === 'GET')) { 
      return Promise.resolve({
        ok: true,
        json: () => Promise.resolve([ 
        
          { id: 1, taskName: "Task 1", deadline: "05 Jan 2027"},
          { id: 2, taskName: "Task 2", deadline: "15 Feb 2027"},
          { id: 3, taskName: "Task 3", deadline: "18 Feb 2027"}
        ])
      });
    }

    if(url.includes("todos/archived")){
      return Promise.resolve({
        ok: true,
        json: () => Promise.resolve([]) 
      });
    }

    if(url.includes("/duplicate")){
       return Promise.resolve({
        ok: true,
        json: () => Promise.resolve({
          id: 4, taskName: "Task 1", deadline: "05 Jan 2027"
        }) 
      });
    }
    return Promise.reject (new Error("Unknown fetch"));
  });

  render(<TodoList />);

  await screen.findByDisplayValue("Task 1");

  const duplicateButtons = screen.getAllByRole("button", { name: /duplicate/i });
  await user.click(duplicateButtons[0]);

  const duplicated = await screen.findAllByDisplayValue("Task 1");
  expect(duplicated.length).toBe(2);
});

});
