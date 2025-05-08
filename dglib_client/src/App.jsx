import { RouterProvider } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import root from './routers/root';
import './App.css'

const queryClient = new QueryClient();

function App() {

  return (
    <QueryClientProvider client={queryClient}>
    <RouterProvider router = {root}></RouterProvider>
    </QueryClientProvider>
  );
}

export default App
