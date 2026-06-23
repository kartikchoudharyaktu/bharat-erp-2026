import React from 'react';
import { NavLink } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { 
  LayoutDashboard, Users, Factory, FolderKanban, 
  Building2, FileText, BarChart3, Settings,
  LogOut, UserCircle, Gift
} from 'lucide-react';
import { CREATOR } from '../../utils/constants';

const Sidebar = () => {
  const { sidebarOpen } = useSelector((state) => state.app);

  const menuItems = [
    { path: '/', icon: LayoutDashboard, label: 'Dashboard' },
    { path: '/hr', icon: Users, label: 'HR Management' },
    { path: '/manufacturing', icon: Factory, label: 'Manufacturing' },
    { path: '/projects', icon: FolderKanban, label: 'Projects' },
    { path: '/company', icon: Building2, label: 'Multi-Company' },
    { path: '/invoices', icon: FileText, label: 'Invoices' },
    { path: '/reports', icon: BarChart3, label: 'Reports' },
    { path: '/settings', icon: Settings, label: 'Settings' },
  ];

  return (
    <>
      {/* Mobile Overlay */}
      {sidebarOpen && (
        <div className="fixed inset-0 bg-black/50 z-40 lg:hidden" />
      )}

      <aside className={`
        fixed left-0 top-16 h-[calc(100vh-4rem)] bg-white border-r border-gray-200
        transition-all duration-300 z-40 flex flex-col
        ${sidebarOpen ? 'w-64 translate-x-0' : 'w-0 -translate-x-full lg:w-20 lg:translate-x-0'}
      `}>
        {/* Navigation Items */}
        <nav className="flex-1 overflow-y-auto p-3 scrollbar-thin">
          <div className="space-y-1">
            {menuItems.map((item) => (
              <NavLink
                key={item.path}
                to={item.path}
                className={({ isActive }) => `
                  flex items-center gap-3 px-3 py-2.5 rounded-lg transition-all duration-200
                  ${isActive 
                    ? 'bg-primary-50 text-primary-600' 
                    : 'text-gray-600 hover:bg-gray-100 hover:text-gray-800'
                  }
                  ${!sidebarOpen ? 'lg:justify-center' : ''}
                `}
                title={!sidebarOpen ? item.label : ''}
              >
                <item.icon className={`w-5 h-5 flex-shrink-0 ${!sidebarOpen ? 'lg:w-6 lg:h-6' : ''}`} />
                {sidebarOpen && <span className="text-sm font-medium">{item.label}</span>}
              </NavLink>
            ))}
          </div>
        </nav>

        {/* Bottom Section */}
        <div className="border-t border-gray-200 p-3">
          {sidebarOpen ? (
            <div className="flex items-center gap-3 px-3 py-2 rounded-lg bg-gray-50">
              <div className="w-8 h-8 rounded-full bg-primary-100 flex items-center justify-center text-primary-600 text-sm font-bold">
                KC
              </div>
              <div className="flex-1 min-w-0">
                <p className="text-xs font-semibold text-gray-700 truncate">
                  Kartik Choudhary
                </p>
                <p className="text-xs text-gray-400 truncate">
                  B.Tech CSE | RGIT
                </p>
              </div>
            </div>
          ) : (
            <div className="flex justify-center">
              <div className="w-8 h-8 rounded-full bg-primary-100 flex items-center justify-center text-primary-600 text-sm font-bold">
                KC
              </div>
            </div>
          )}
        </div>
      </aside>
    </>
  );
};

export default Sidebar;
