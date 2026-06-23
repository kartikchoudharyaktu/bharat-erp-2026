import React from 'react';
import { Github, Linkedin, Mail, Phone, MapPin } from 'lucide-react';

const Footer = () => {
  const currentYear = new Date().getFullYear();
  
  return (
    <footer className="bg-white border-t border-gray-200 mt-auto">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {/* Brand Section */}
          <div className="flex flex-col items-start">
            <div className="flex items-center gap-2 mb-2">
              <span className="text-2xl font-bold text-primary-600">🇮🇳</span>
              <span className="text-xl font-bold text-gray-800">BHARAT ERP 2026</span>
            </div>
            <p className="text-sm text-gray-500">
              Made in India 🇮🇳 | Complete ERP Solution
            </p>
            <p className="text-xs text-gray-400 mt-1">
              v1.0.0 | {currentYear}
            </p>
          </div>

          {/* Creator Info */}
          <div className="flex flex-col items-start">
            <h3 className="text-sm font-semibold text-gray-700 mb-2">👨‍💻 Created By</h3>
            <p className="text-sm text-gray-600 font-medium">Kartik Choudhary</p>
            <p className="text-xs text-gray-500">B.Tech CSE | RGIT, Ghaziabad</p>
            <p className="text-xs text-gray-500">AKTU, Lucknow</p>
            <div className="flex items-center gap-2 mt-1">
              <Phone className="w-3 h-3 text-gray-400" />
              <span className="text-xs text-gray-500">9457809806</span>
            </div>
            <div className="flex items-center gap-2">
              <Mail className="w-3 h-3 text-gray-400" />
              <span className="text-xs text-gray-500">kartikmzn7@gmail.com</span>
            </div>
          </div>

          {/* Social Links */}
          <div className="flex flex-col items-start">
            <h3 className="text-sm font-semibold text-gray-700 mb-2">🔗 Connect</h3>
            <div className="flex flex-col gap-2">
              <a
                href="https://www.linkedin.com/in/kartik-choudharyaktu"
                target="_blank"
                rel="noopener noreferrer"
                className="flex items-center gap-2 text-sm text-gray-600 hover:text-primary-600 transition-colors"
              >
                <Linkedin className="w-4 h-4" />
                LinkedIn: kartik-choudharyaktu
              </a>
              <a
                href="https://github.com/kartikchoudharyaktu"
                target="_blank"
                rel="noopener noreferrer"
                className="flex items-center gap-2 text-sm text-gray-600 hover:text-primary-600 transition-colors"
              >
                <Github className="w-4 h-4" />
                GitHub: kartikchoudharyaktu
              </a>
              <div className="flex items-center gap-2 text-sm text-gray-500">
                <MapPin className="w-4 h-4" />
                Ghaziabad, UP, Delhi NCR
              </div>
            </div>
          </div>
        </div>

        {/* Bottom Bar */}
        <div className="mt-4 pt-4 border-t border-gray-200 text-center">
          <p className="text-xs text-gray-400">
            © {currentYear} Bharat ERP. All rights reserved. | Made with ❤️ in India 🇮🇳
          </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
