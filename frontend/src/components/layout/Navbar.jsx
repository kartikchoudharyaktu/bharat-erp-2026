import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  Menu, X, Bell, User, LogOut, Settings, 
  Sun, Moon, ChevronDown 
} from 'lucide-react';
import { useSelector, useDispatch } from 'react-redux';
import { toggleSidebar } from '../../store/slices/appSlice';
import { logout } from '../../store/slices/authSlice';
import { CREATOR } from '../../utils/constants';

const Navbar = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { user } = useSelector((state) => state.auth);
  const { theme, sidebarOpen } = useSelector((state) => state.app);
  const [showDropdown, setShowDropdown] = useState(false);
  const [showNotifications, setShowNotifications] = useState(false);

  const handleLogout = () => {
    dispatch(logout());
    navigate('/login');
  };

  const handleToggleSidebar = () => {
    dispatch(toggleSidebar());
  };

  return (
    <nav className="bg-white border-b border-gray-200 fixed top-0 left-0 right-0 z-50 h-16">
      <div className="flex items-center justify-between h-full px-4">
        {/* Left Section */}
        <div className="flex items-center gap-3">
          <button
            onClick={handleToggleSidebar}
            className="p-2 rounded-lg hover:bg-gray-100 transition-colors lg:hidden"
          >
            {sidebarOpen ? <X className="w-5 h-5" /> : <Menu className="w-5 h-5" />}
          </button>
          
          <div className="flex items-center gap-2">
            <span className="text-2xl">🇮🇳</span>
            <span className="text-xl font-bold text-primary-600 hidden sm:block">
              Bharat ERP
            </span>
            <span className="text-xs text-gray-400 hidden md:block">2026</span>
          </div>
        </div>

        {/* Center Section - Creator Credit */}
        <div className="hidden md:flex items-center gap-2 bg-gray-50 px-3 py-1 rounded-full">
          <span className="text-xs text-gray-500">Created by</span>
          <span className="text-xs font-semibold text-primary-600">
            {CREATOR.name}
          </span>
          <span className="text-xs text-gray-400">|</span>
          <span className="text-xs text-gray-500">{CREATOR.title}</span>
        </div>

        {/* Right Section */}
        <div className="flex items-center gap-2">
          {/* Theme Toggle */}
          <button className="p-2 rounded-lg hover:bg-gray-100 transition-colors">
            {theme === 'light' ? <Moon className="w-5 h-5" /> : <Sun className="w-5 h-5" />}
          </button>

          {/* Notifications */}
          <button
            onClick={() => setShowNotifications(!showNotifications)}
            className="p-2 rounded-lg hover:bg-gray-100 transition-colors relative"
          >
            <Bell className="w-5 h-5" />
            <span className="absolute top-1 right-1 w-2 h-2 bg-red-500 rounded-full"></span>
          </button>

          {/* User Dropdown */}
          <div className="relative">
            <button
              onClick={() => setShowDropdown(!showDropdown)}
              className="flex items-center gap-2 p-2 rounded-lg hover:bg-gray-100 transition-colors"
            >
              <div className="w-8 h-8 rounded-full bg-primary-100 flex items-center justify-center text-primary-600 font-semibold text-sm">
                {user?.fullName?.[0] || 'K'}
              </div>
              <span className="hidden sm:block text-sm font-medium text-gray-700">
                {user?.fullName || 'Kartik'}
              </span>
              <ChevronDown className="w-4 h-4 text-gray-400" />
            </button>

            {showDropdown && (
              <div className="absolute right-0 mt-2 w-56 bg-white rounded-xl shadow-lg border border-gray-200 py-1 z-50">
                <div className="px-4 py-3 border-b border-gray-100">
                  <p className="text-sm font-semibold text-gray-800">{user?.fullName || 'Kartik Choudhary'}</p>
                  <p className="text-xs text-gray-500">{user?.email || 'kartikmzn7@gmail.com'}</p>
                </div>
                <button className="w-full px-4 py-2 text-sm text-gray-700 hover:bg-gray-50 flex items-center gap-2">
                  <User className="w-4 h-4" />
                  Profile
                </button>
                <button className="w-full px-4 py-2 text-sm text-gray-700 hover:bg-gray-50 flex items-center gap-2">
                  <Settings className="w-4 h-4" />
                  Settings
                </button>
                <hr className="my-1" />
                <button
                  onClick={handleLogout}
                  className="w-full px-4 py-2 text-sm text-red-600 hover:bg-red-50 flex items-center gap-2"
                >
                  <LogOut className="w-4 h-4" />
                  Logout
                </button>
              </div>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
