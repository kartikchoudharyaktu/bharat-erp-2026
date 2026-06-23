import React from 'react';
import { useSelector } from 'react-redux';
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import Footer from './Footer';

const Layout = ({ children }) => {
  const { sidebarOpen } = useSelector((state) => state.app);

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <div className="flex pt-16">
        <Sidebar />
        <main className={`flex-1 transition-all duration-300 ${sidebarOpen ? 'ml-64' : 'ml-0'}`}>
          <div className="p-4 md:p-6 min-h-[calc(100vh-8rem)]">
            {children}
          </div>
          <Footer />
        </main>
      </div>
    </div>
  );
};

export default Layout;
