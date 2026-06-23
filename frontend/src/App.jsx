import React, { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import {
    BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend,
    LineChart, Line, PieChart, Pie, Cell, AreaChart, Area,
    ResponsiveContainer
} from 'recharts';

axios.defaults.baseURL = 'http://localhost:8080/api';

// ============================================================
// CREATOR DETAILS
// ============================================================
const CREATOR = {
  name: 'Kartik Choudhary',
  college: 'Raj Kumar Goel Institute of Technology, Ghaziabad',
  university: 'Dr. A.P.J. Abdul Kalam Technical University (AKTU)',
  location: 'Ghaziabad, UP, Delhi NCR (BHARAT "HINDUSTAAN")',
  email: 'kartikmzn7@gmail.com',
  phone: '9457809806',
  linkedin: 'https://www.linkedin.com/in/kartik-choudharyaktu',
  github: 'https://github.com/kartikchoudharyaktu',
  instagram: 'https://instagram.com/kartik_choudhary_aktu',
  gstin: '09ABCDE1234F1Z5'
};

// ============================================================
// TABS FOR SIDEBARS
// ============================================================
const leftTabs = [
  { id: 'dashboard', label: 'Dashboard', icon: '📊' },
  { id: 'customers', label: 'Customers', icon: '👥' },
  { id: 'products', label: 'Products', icon: '📦' },
  { id: 'vendors', label: 'Vendors', icon: '🏢' },
  { id: 'invoices', label: 'Invoices', icon: '📄' },
  { id: 'purchase-orders', label: 'Purchase Orders', icon: '📋' },
];

const rightTabs = [
  { id: 'hr-employees', label: 'HR Management', icon: '👨‍💼' },
  { id: 'hr-leaves', label: 'Leaves', icon: '📅' },
  { id: 'mfg-bom', label: 'BOM', icon: '📋' },
  { id: 'mfg-production', label: 'Production', icon: '🏭' },
  { id: 'projects', label: 'Projects', icon: '📊' },
  { id: 'tasks', label: 'Tasks', icon: '✅' },
  { id: 'timesheets', label: 'Timesheets', icon: '⏱️' },
  { id: 'companies', label: 'Companies', icon: '🏢' },
  { id: 'budgets', label: 'Budgets', icon: '💰' },
  { id: 'sales-report', label: 'Sales Report', icon: '📈' },
  { id: 'purchase-report', label: 'Purchase Report', icon: '📉' },
  { id: 'stock-report', label: 'Stock Report', icon: '📦' },
  { id: 'gst-report', label: 'GST Report', icon: '🧾' },
  { id: 'customer-report', label: 'Customer Report', icon: '👥' },
  { id: 'vendor-report', label: 'Vendor Report', icon: '🏢' },
  { id: 'trial-balance', label: 'Trial Balance', icon: '⚖️' },
  { id: 'profit-loss', label: 'P&L', icon: '💰' },
  { id: 'chat', label: 'AI Chat', icon: '💬' },
  { id: 'search', label: 'AI Search', icon: '🔍' },
  { id: 'auto-invoice', label: 'Auto Invoice', icon: '⚡' },
  { id: 'anomaly', label: 'Anomaly', icon: '🛡️' },
  { id: 'predict', label: 'Predict', icon: '📊' },
  { id: 'qr', label: 'QR Scanner', icon: '📱' },
  { id: 'extract', label: 'Extract', icon: '📄' },
  { id: 'agents', label: 'AI Agents', icon: '🤖' },
  { id: 'settings', label: 'Settings', icon: '⚙️' },
];

function App() {
    // ==================== STATE ====================
    const [activeTab, setActiveTab] = useState('dashboard');
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState({ text: '', type: '' });
    const [isDarkMode, setIsDarkMode] = useState(false);
    const [leftOpen, setLeftOpen] = useState(true);
    const [rightOpen, setRightOpen] = useState(true);
    const [currentTime, setCurrentTime] = useState(new Date());
    const [showModal, setShowModal] = useState(false);
    const [modalType, setModalType] = useState('');

    // ==================== TIME UPDATE ====================
    useEffect(() => {
        const timer = setInterval(() => setCurrentTime(new Date()), 1000);
        return () => clearInterval(timer);
    }, []);

    const formatDate = (date) => {
        return date.toLocaleDateString('en-IN', { 
            day: '2-digit', month: '2-digit', year: 'numeric' 
        });
    };

    const formatTime = (date) => {
        return date.toLocaleTimeString('en-IN', { 
            hour: '2-digit', minute: '2-digit', second: '2-digit'
        });
    };

    const toggleLeft = () => setLeftOpen(!leftOpen);
    const toggleRight = () => setRightOpen(!rightOpen);

    // ==================== PHASE 2: CUSTOMERS ====================
    const [customers, setCustomers] = useState([]);
    const [customerForm, setCustomerForm] = useState({
        code: '', name: '', gstin: '', phone: '', email: '', address: '', city: '', state: '', creditLimit: 0
    });
    const [editingCustomerId, setEditingCustomerId] = useState(null);

    const fetchCustomers = async () => {
        try { const res = await axios.get('/customers'); setCustomers(Array.isArray(res.data) ? res.data : []); } catch (e) { setCustomers([]); }
    };

    const handleCustomerSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingCustomerId) {
                await axios.put(`/customers/${editingCustomerId}`, customerForm);
                setMessage({ text: 'Customer updated!', type: 'success' });
            } else {
                await axios.post('/customers', customerForm);
                setMessage({ text: 'Customer added!', type: 'success' });
            }
            resetCustomerForm();
            fetchCustomers();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteCustomer = async (id) => {
        if (!window.confirm('Delete this customer?')) return;
        try { await axios.delete(`/customers/${id}`); setMessage({ text: 'Customer deleted!', type: 'success' }); fetchCustomers(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetCustomerForm = () => {
        setCustomerForm({ code: '', name: '', gstin: '', phone: '', email: '', address: '', city: '', state: '', creditLimit: 0 });
        setEditingCustomerId(null);
    };

    // ==================== PHASE 2: PRODUCTS ====================
    const [products, setProducts] = useState([]);
    const [productForm, setProductForm] = useState({
        code: '', name: '', hsn: '', unit: '', gstRate: 18, purchasePrice: 0, sellingPrice: 0, stock: 0, minStock: 0
    });
    const [editingProductId, setEditingProductId] = useState(null);

    const fetchProducts = async () => {
        try { const res = await axios.get('/products'); setProducts(Array.isArray(res.data) ? res.data : []); } catch (e) { setProducts([]); }
    };

    const handleProductSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingProductId) {
                await axios.put(`/products/${editingProductId}`, productForm);
                setMessage({ text: 'Product updated!', type: 'success' });
            } else {
                await axios.post('/products', productForm);
                setMessage({ text: 'Product added!', type: 'success' });
            }
            resetProductForm();
            fetchProducts();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteProduct = async (id) => {
        if (!window.confirm('Delete this product?')) return;
        try { await axios.delete(`/products/${id}`); setMessage({ text: 'Product deleted!', type: 'success' }); fetchProducts(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetProductForm = () => {
        setProductForm({ code: '', name: '', hsn: '', unit: '', gstRate: 18, purchasePrice: 0, sellingPrice: 0, stock: 0, minStock: 0 });
        setEditingProductId(null);
    };

    // ==================== PHASE 4: VENDORS ====================
    const [vendors, setVendors] = useState([]);
    const [vendorForm, setVendorForm] = useState({
        code: '', name: '', gstin: '', phone: '', email: '', address: '', city: '', state: ''
    });
    const [editingVendorId, setEditingVendorId] = useState(null);

    const fetchVendors = async () => {
        try { const res = await axios.get('/vendors'); setVendors(Array.isArray(res.data) ? res.data : []); } catch (e) { setVendors([]); }
    };

    const handleVendorSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingVendorId) {
                await axios.put(`/vendors/${editingVendorId}`, vendorForm);
                setMessage({ text: 'Vendor updated!', type: 'success' });
            } else {
                await axios.post('/vendors', vendorForm);
                setMessage({ text: 'Vendor added!', type: 'success' });
            }
            resetVendorForm();
            fetchVendors();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteVendor = async (id) => {
        if (!window.confirm('Delete this vendor?')) return;
        try { await axios.delete(`/vendors/${id}`); setMessage({ text: 'Vendor deleted!', type: 'success' }); fetchVendors(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetVendorForm = () => {
        setVendorForm({ code: '', name: '', gstin: '', phone: '', email: '', address: '', city: '', state: '' });
        setEditingVendorId(null);
    };

    // ==================== PHASE 3: INVOICES ====================
    const [invoices, setInvoices] = useState([]);
    const [invoiceItems, setInvoiceItems] = useState([]);
    const [invoiceForm, setInvoiceForm] = useState({
        customerId: '', customerName: '', customerGstin: '', customerAddress: ''
    });

    const fetchInvoices = async () => {
        try { const res = await axios.get('/invoices'); setInvoices(Array.isArray(res.data) ? res.data : []); } catch (e) { setInvoices([]); }
    };

    const handleInvoiceSubmit = async (e) => {
        e.preventDefault();
        if (invoiceItems.length === 0) {
            setMessage({ text: 'Add at least one item!', type: 'danger' });
            return;
        }
        setLoading(true);
        try {
            const data = {
                customerId: invoiceForm.customerId,
                customerName: invoiceForm.customerName,
                customerGstin: invoiceForm.customerGstin,
                customerAddress: invoiceForm.customerAddress,
                items: invoiceItems
            };
            const res = await axios.post('/invoices', data);
            setMessage({ text: `Invoice ${res.data.invoiceNumber} created!`, type: 'success' });
            setInvoiceItems([]);
            resetInvoiceForm();
            fetchInvoices();
            fetchAllData();
            setShowModal(false);
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteInvoice = async (id) => {
        if (!window.confirm('Delete this invoice?')) return;
        try { await axios.delete(`/invoices/${id}`); setMessage({ text: 'Invoice deleted!', type: 'success' }); fetchInvoices(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetInvoiceForm = () => {
        setInvoiceForm({ customerId: '', customerName: '', customerGstin: '', customerAddress: '' });
        setInvoiceItems([]);
    };

    const addInvoiceItem = (product) => {
        const existing = invoiceItems.find(i => i.name === product.name);
        if (existing) {
            setInvoiceItems(invoiceItems.map(i =>
                i.name === product.name ? { ...i, qty: i.qty + 1, amount: (i.qty + 1) * i.rate, total: (i.qty + 1) * i.rate * (1 + i.gstRate / 100) } : i
            ));
        } else {
            const newItem = {
                name: product.name,
                hsn: product.hsn || '',
                qty: 1,
                rate: product.sellingPrice,
                gstRate: product.gstRate || 18,
                amount: product.sellingPrice,
                gstAmount: product.sellingPrice * (product.gstRate || 18) / 100,
                total: product.sellingPrice * (1 + (product.gstRate || 18) / 100)
            };
            setInvoiceItems([...invoiceItems, newItem]);
        }
    };

    const removeInvoiceItem = (index) => {
        setInvoiceItems(invoiceItems.filter((_, i) => i !== index));
    };

    // ==================== PHASE 4: PURCHASE ORDERS ====================
    const [purchaseOrders, setPurchaseOrders] = useState([]);
    const [poItems, setPoItems] = useState([]);
    const [poForm, setPoForm] = useState({
        vendorId: '', vendorName: '', vendorGstin: '', deliveryDate: ''
    });

    const fetchPurchaseOrders = async () => {
        try { const res = await axios.get('/purchase-orders'); setPurchaseOrders(Array.isArray(res.data) ? res.data : []); } catch (e) { setPurchaseOrders([]); }
    };

    const handlePOSubmit = async (e) => {
        e.preventDefault();
        if (poItems.length === 0) {
            setMessage({ text: 'Add at least one item!', type: 'danger' });
            return;
        }
        setLoading(true);
        try {
            const data = {
                vendorId: poForm.vendorId,
                vendorName: poForm.vendorName,
                vendorGstin: poForm.vendorGstin,
                deliveryDate: poForm.deliveryDate || new Date().toISOString().split('T')[0],
                items: poItems
            };
            const res = await axios.post('/purchase-orders', data);
            setMessage({ text: `PO ${res.data.poNumber} created!`, type: 'success' });
            setPoItems([]);
            resetPOForm();
            fetchPurchaseOrders();
            fetchAllData();
            setShowModal(false);
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deletePO = async (id) => {
        if (!window.confirm('Delete this PO?')) return;
        try { await axios.delete(`/purchase-orders/${id}`); setMessage({ text: 'PO deleted!', type: 'success' }); fetchPurchaseOrders(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetPOForm = () => {
        setPoForm({ vendorId: '', vendorName: '', vendorGstin: '', deliveryDate: '' });
        setPoItems([]);
    };

    const addPOItem = (product) => {
        const existing = poItems.find(i => i.name === product.name);
        if (existing) {
            setPoItems(poItems.map(i =>
                i.name === product.name ? { ...i, qty: i.qty + 1, amount: (i.qty + 1) * i.rate, total: (i.qty + 1) * i.rate * (1 + i.gstRate / 100) } : i
            ));
        } else {
            const newItem = {
                name: product.name,
                hsn: product.hsn || '',
                qty: 1,
                rate: product.purchasePrice,
                gstRate: product.gstRate || 18,
                amount: product.purchasePrice,
                gstAmount: product.purchasePrice * (product.gstRate || 18) / 100,
                total: product.purchasePrice * (1 + (product.gstRate || 18) / 100)
            };
            setPoItems([...poItems, newItem]);
        }
    };

    const removePOItem = (index) => {
        setPoItems(poItems.filter((_, i) => i !== index));
    };

    // ==================== PHASE 5: REPORTS ====================
    const [dashboardStats, setDashboardStats] = useState({});
    const [salesReport, setSalesReport] = useState({});
    const [purchaseReport, setPurchaseReport] = useState({});
    const [gstReport, setGstReport] = useState({});
    const [stockReport, setStockReport] = useState({});
    const [customerReport, setCustomerReport] = useState({});
    const [vendorReport, setVendorReport] = useState({});
    const [trialBalance, setTrialBalance] = useState({});
    const [profitLoss, setProfitLoss] = useState({});

    const fetchDashboardStats = async () => {
        try { const res = await axios.get('/reports/dashboard'); setDashboardStats(res.data); } catch (e) { }
    };
    const fetchSalesReport = async () => {
        try { const res = await axios.get('/reports/sales'); setSalesReport(res.data); } catch (e) { }
    };
    const fetchPurchaseReport = async () => {
        try { const res = await axios.get('/reports/purchase'); setPurchaseReport(res.data); } catch (e) { }
    };
    const fetchGSTReport = async () => {
        try { const res = await axios.get('/reports/gst'); setGstReport(res.data); } catch (e) { }
    };
    const fetchStockReport = async () => {
        try { const res = await axios.get('/reports/stock'); setStockReport(res.data); } catch (e) { }
    };
    const fetchCustomerReport = async () => {
        try { const res = await axios.get('/reports/customers'); setCustomerReport(res.data); } catch (e) { }
    };
    const fetchVendorReport = async () => {
        try { const res = await axios.get('/reports/vendors'); setVendorReport(res.data); } catch (e) { }
    };
    const fetchTrialBalance = async () => {
        try { const res = await axios.get('/reports/trial-balance'); setTrialBalance(res.data); } catch (e) { }
    };
    const fetchProfitLoss = async () => {
        try { const res = await axios.get('/reports/profit-loss'); setProfitLoss(res.data); } catch (e) { }
    };

    // ==================== PHASE 6: AI ====================
    const [chatMessages, setChatMessages] = useState([
        { sender: 'ai', text: '👋 Hello! I am your AI Assistant. Ask me about vendors, products, sales, stock, or anything!' }
    ]);
    const [chatInput, setChatInput] = useState('');
    const [searchKeyword, setSearchKeyword] = useState('');
    const [searchResults, setSearchResults] = useState([]);
    const [anomalies, setAnomalies] = useState([]);
    const [prediction, setPrediction] = useState(null);
    const [qrData, setQrData] = useState('');
    const [qrResult, setQrResult] = useState(null);
    const [extractText, setExtractText] = useState('');
    const [extractResult, setExtractResult] = useState(null);
    const [autoInvoiceData, setAutoInvoiceData] = useState({
        customerName: '',
        items: [{ name: '', qty: 1, rate: 0 }]
    });
    const [autoInvoiceResult, setAutoInvoiceResult] = useState(null);
    const [agentResults, setAgentResults] = useState([]);

    const sendChat = async () => {
        if (!chatInput.trim()) return;
        setChatMessages([...chatMessages, { sender: 'user', text: chatInput }]);
        setLoading(true);
        try {
            const res = await axios.post('/ai/chat', { query: chatInput });
            const replies = res.data.replies || ['I am processing your request...'];
            replies.forEach(reply => {
                setChatMessages(prev => [...prev, { sender: 'ai', text: reply }]);
            });
            setChatInput('');
        } catch (error) {
            setMessage({ text: 'AI Chat error!', type: 'danger' });
        } finally {
            setLoading(false);
        }
    };

    const handleSmartSearch = async () => {
        if (!searchKeyword.trim()) {
            setMessage({ text: 'Enter search keyword!', type: 'warning' });
            return;
        }
        setLoading(true);
        try {
            const res = await axios.get(`/ai/search?keyword=${searchKeyword}`);
            setSearchResults(res.data.results || []);
            setMessage({ text: `Found ${res.data.total || 0} results!`, type: 'success' });
        } catch (error) {
            setMessage({ text: 'Search error!', type: 'danger' });
        } finally {
            setLoading(false);
        }
    };

    const handleAnomalyDetection = async () => {
        setLoading(true);
        try {
            const res = await axios.get('/ai/anomalies');
            setAnomalies(res.data.anomalies || []);
            setMessage({ text: `Found ${res.data.totalAnomalies || 0} anomalies!`, type: res.data.totalAnomalies > 0 ? 'warning' : 'success' });
        } catch (error) {
            setMessage({ text: 'Anomaly detection error!', type: 'danger' });
        } finally {
            setLoading(false);
        }
    };

    const handlePredict = async () => {
        setLoading(true);
        try {
            const res = await axios.get('/ai/predict?days=7');
            setPrediction(res.data);
            setMessage({ text: 'Prediction generated!', type: 'success' });
        } catch (error) {
            setMessage({ text: 'Prediction error!', type: 'danger' });
        } finally {
            setLoading(false);
        }
    };

    const handleQRScan = async () => {
        if (!qrData.trim()) {
            setMessage({ text: 'Enter QR data!', type: 'warning' });
            return;
        }
        setLoading(true);
        try {
            const res = await axios.post('/ai/scan-qr', { data: qrData });
            setQrResult(res.data);
            setMessage({ text: res.data.message || 'QR scanned!', type: 'success' });
        } catch (error) {
            setMessage({ text: 'QR scan error!', type: 'danger' });
        } finally {
            setLoading(false);
        }
    };

    const handleExtract = async () => {
        if (!extractText.trim()) {
            setMessage({ text: 'Enter text to extract!', type: 'warning' });
            return;
        }
        setLoading(true);
        try {
            const res = await axios.post('/ai/extract', { text: extractText });
            setExtractResult(res.data);
            setMessage({ text: 'Data extracted!', type: 'success' });
        } catch (error) {
            setMessage({ text: 'Extraction error!', type: 'danger' });
        } finally {
            setLoading(false);
        }
    };

    const handleAutoInvoice = async () => {
        const { customerName, items } = autoInvoiceData;
        if (!customerName.trim() || items.some(i => !i.name.trim() || i.qty <= 0)) {
            setMessage({ text: 'Fill all fields correctly!', type: 'warning' });
            return;
        }
        setLoading(true);
        try {
            const res = await axios.post('/ai/auto-invoice', { customerName, items });
            setAutoInvoiceResult(res.data);
            setMessage({ text: res.data.message || 'Invoice generated!', type: 'success' });
        } catch (error) {
            setMessage({ text: 'Auto invoice error!', type: 'danger' });
        } finally {
            setLoading(false);
        }
    };

    const handleRunAgents = async () => {
        setLoading(true);
        try {
            const res = await axios.post('/ai/run-agents');
            setAgentResults(res.data.agents || []);
            setMessage({ text: 'Agents executed!', type: 'success' });
        } catch (error) {
            setMessage({ text: 'Agent error!', type: 'danger' });
        } finally {
            setLoading(false);
        }
    };

    const addAutoInvoiceItem = () => {
        setAutoInvoiceData({
            ...autoInvoiceData,
            items: [...autoInvoiceData.items, { name: '', qty: 1, rate: 0 }]
        });
    };

    const updateAutoInvoiceItem = (index, field, value) => {
        const updated = [...autoInvoiceData.items];
        updated[index][field] = value;
        setAutoInvoiceData({ ...autoInvoiceData, items: updated });
    };

    const removeAutoInvoiceItem = (index) => {
        const updated = autoInvoiceData.items.filter((_, i) => i !== index);
        setAutoInvoiceData({ ...autoInvoiceData, items: updated });
    };

    // ==================== PHASE 7: HR - EMPLOYEES ====================
    const [employees, setEmployees] = useState([]);
    const [employeeForm, setEmployeeForm] = useState({
        employeeId: '', firstName: '', lastName: '', email: '', phone: '',
        department: '', designation: '', joiningDate: '', salary: 0,
        gender: '', dateOfBirth: '', bloodGroup: '', status: 'ACTIVE'
    });
    const [editingEmployeeId, setEditingEmployeeId] = useState(null);

    const fetchEmployees = async () => {
        try { const res = await axios.get('/employees'); setEmployees(Array.isArray(res.data) ? res.data : []); } catch (e) { setEmployees([]); }
    };

    const handleEmployeeSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingEmployeeId) {
                await axios.put(`/employees/${editingEmployeeId}`, employeeForm);
                setMessage({ text: 'Employee updated!', type: 'success' });
            } else {
                await axios.post('/employees', employeeForm);
                setMessage({ text: 'Employee added!', type: 'success' });
            }
            resetEmployeeForm();
            fetchEmployees();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteEmployee = async (id) => {
        if (!window.confirm('Delete this employee?')) return;
        try { await axios.delete(`/employees/${id}`); setMessage({ text: 'Employee deleted!', type: 'success' }); fetchEmployees(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetEmployeeForm = () => {
        setEmployeeForm({ employeeId: '', firstName: '', lastName: '', email: '', phone: '', department: '', designation: '', joiningDate: '', salary: 0, gender: '', dateOfBirth: '', bloodGroup: '', status: 'ACTIVE' });
        setEditingEmployeeId(null);
    };

    // ==================== PHASE 7: LEAVES ====================
    const [leaves, setLeaves] = useState([]);
    const [leaveForm, setLeaveForm] = useState({
        employeeId: '', leaveType: 'CASUAL', startDate: '', endDate: '',
        reason: '', status: 'PENDING'
    });
    const [editingLeaveId, setEditingLeaveId] = useState(null);

    const fetchLeaves = async () => {
        try { const res = await axios.get('/leaves'); setLeaves(Array.isArray(res.data) ? res.data : []); } catch (e) { setLeaves([]); }
    };

    const handleLeaveSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingLeaveId) {
                await axios.put(`/leaves/${editingLeaveId}`, leaveForm);
                setMessage({ text: 'Leave updated!', type: 'success' });
            } else {
                await axios.post('/leaves', leaveForm);
                setMessage({ text: 'Leave added!', type: 'success' });
            }
            resetLeaveForm();
            fetchLeaves();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteLeave = async (id) => {
        if (!window.confirm('Delete this leave?')) return;
        try { await axios.delete(`/leaves/${id}`); setMessage({ text: 'Leave deleted!', type: 'success' }); fetchLeaves(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const approveLeave = async (id) => {
        try { await axios.put(`/leaves/${id}/approve?approvedBy=Admin`); setMessage({ text: 'Leave approved!', type: 'success' }); fetchLeaves(); } catch (e) { setMessage({ text: 'Error approving!', type: 'danger' }); }
    };

    const rejectLeave = async (id) => {
        try { await axios.put(`/leaves/${id}/reject`); setMessage({ text: 'Leave rejected!', type: 'success' }); fetchLeaves(); } catch (e) { setMessage({ text: 'Error rejecting!', type: 'danger' }); }
    };

    const resetLeaveForm = () => {
        setLeaveForm({ employeeId: '', leaveType: 'CASUAL', startDate: '', endDate: '', reason: '', status: 'PENDING' });
        setEditingLeaveId(null);
    };

    // ==================== PHASE 8: BOM ====================
    const [boms, setBoms] = useState([]);
    const [bomForm, setBomForm] = useState({
        productId: '', bomName: '', version: '1.0', status: 'ACTIVE'
    });
    const [editingBomId, setEditingBomId] = useState(null);

    const fetchBoms = async () => {
        try { const res = await axios.get('/boms'); setBoms(Array.isArray(res.data) ? res.data : []); } catch (e) { setBoms([]); }
    };

    const handleBomSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingBomId) {
                await axios.put(`/boms/${editingBomId}`, bomForm);
                setMessage({ text: 'BOM updated!', type: 'success' });
            } else {
                await axios.post('/boms', bomForm);
                setMessage({ text: 'BOM added!', type: 'success' });
            }
            resetBomForm();
            fetchBoms();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteBom = async (id) => {
        if (!window.confirm('Delete this BOM?')) return;
        try { await axios.delete(`/boms/${id}`); setMessage({ text: 'BOM deleted!', type: 'success' }); fetchBoms(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetBomForm = () => {
        setBomForm({ productId: '', bomName: '', version: '1.0', status: 'ACTIVE' });
        setEditingBomId(null);
    };

    // ==================== PHASE 8: PRODUCTION ORDERS ====================
    const [productionOrders, setProductionOrders] = useState([]);
    const [productionForm, setProductionForm] = useState({
        productId: '', quantity: 0, startDate: '', endDate: '',
        status: 'PLANNED', priority: 'MEDIUM'
    });
    const [editingProductionId, setEditingProductionId] = useState(null);

    const fetchProductionOrders = async () => {
        try { const res = await axios.get('/production-orders'); setProductionOrders(Array.isArray(res.data) ? res.data : []); } catch (e) { setProductionOrders([]); }
    };

    const handleProductionSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingProductionId) {
                await axios.put(`/production-orders/${editingProductionId}`, productionForm);
                setMessage({ text: 'Production order updated!', type: 'success' });
            } else {
                await axios.post('/production-orders', productionForm);
                setMessage({ text: 'Production order added!', type: 'success' });
            }
            resetProductionForm();
            fetchProductionOrders();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteProduction = async (id) => {
        if (!window.confirm('Delete this production order?')) return;
        try { await axios.delete(`/production-orders/${id}`); setMessage({ text: 'Production order deleted!', type: 'success' }); fetchProductionOrders(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetProductionForm = () => {
        setProductionForm({ productId: '', quantity: 0, startDate: '', endDate: '', status: 'PLANNED', priority: 'MEDIUM' });
        setEditingProductionId(null);
    };

    // ==================== PHASE 9: PROJECTS ====================
    const [projects, setProjects] = useState([]);
    const [projectForm, setProjectForm] = useState({
        projectName: '', projectCode: '', description: '',
        startDate: '', endDate: '', status: 'PLANNED',
        budget: 0, clientName: '', priority: 'MEDIUM'
    });
    const [editingProjectId, setEditingProjectId] = useState(null);

    const fetchProjects = async () => {
        try { const res = await axios.get('/projects'); setProjects(Array.isArray(res.data) ? res.data : []); } catch (e) { setProjects([]); }
    };

    const handleProjectSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingProjectId) {
                await axios.put(`/projects/${editingProjectId}`, projectForm);
                setMessage({ text: 'Project updated!', type: 'success' });
            } else {
                await axios.post('/projects', projectForm);
                setMessage({ text: 'Project added!', type: 'success' });
            }
            resetProjectForm();
            fetchProjects();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteProject = async (id) => {
        if (!window.confirm('Delete this project?')) return;
        try { await axios.delete(`/projects/${id}`); setMessage({ text: 'Project deleted!', type: 'success' }); fetchProjects(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetProjectForm = () => {
        setProjectForm({ projectName: '', projectCode: '', description: '', startDate: '', endDate: '', status: 'PLANNED', budget: 0, clientName: '', priority: 'MEDIUM' });
        setEditingProjectId(null);
    };

    // ==================== PHASE 9: TASKS ====================
    const [tasks, setTasks] = useState([]);
    const [taskForm, setTaskForm] = useState({
        taskName: '', description: '', projectId: '',
        assignedTo: '', startDate: '', dueDate: '',
        status: 'PENDING', priority: 'MEDIUM', estimatedHours: 0
    });
    const [editingTaskId, setEditingTaskId] = useState(null);

    const fetchTasks = async () => {
        try { const res = await axios.get('/tasks'); setTasks(Array.isArray(res.data) ? res.data : []); } catch (e) { setTasks([]); }
    };

    const handleTaskSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingTaskId) {
                await axios.put(`/tasks/${editingTaskId}`, taskForm);
                setMessage({ text: 'Task updated!', type: 'success' });
            } else {
                await axios.post('/tasks', taskForm);
                setMessage({ text: 'Task added!', type: 'success' });
            }
            resetTaskForm();
            fetchTasks();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteTask = async (id) => {
        if (!window.confirm('Delete this task?')) return;
        try { await axios.delete(`/tasks/${id}`); setMessage({ text: 'Task deleted!', type: 'success' }); fetchTasks(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetTaskForm = () => {
        setTaskForm({ taskName: '', description: '', projectId: '', assignedTo: '', startDate: '', dueDate: '', status: 'PENDING', priority: 'MEDIUM', estimatedHours: 0 });
        setEditingTaskId(null);
    };

    // ==================== PHASE 9: TIMESHEETS ====================
    const [timesheets, setTimesheets] = useState([]);
    const [timesheetForm, setTimesheetForm] = useState({
        employeeId: '', taskId: '', projectId: '',
        entryDate: '', hoursWorked: 0, description: '', status: 'PENDING'
    });
    const [editingTimesheetId, setEditingTimesheetId] = useState(null);

    const fetchTimesheets = async () => {
        try { const res = await axios.get('/timesheets'); setTimesheets(Array.isArray(res.data) ? res.data : []); } catch (e) { setTimesheets([]); }
    };

    const handleTimesheetSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingTimesheetId) {
                await axios.put(`/timesheets/${editingTimesheetId}`, timesheetForm);
                setMessage({ text: 'Timesheet updated!', type: 'success' });
            } else {
                await axios.post('/timesheets', timesheetForm);
                setMessage({ text: 'Timesheet added!', type: 'success' });
            }
            resetTimesheetForm();
            fetchTimesheets();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteTimesheet = async (id) => {
        if (!window.confirm('Delete this timesheet?')) return;
        try { await axios.delete(`/timesheets/${id}`); setMessage({ text: 'Timesheet deleted!', type: 'success' }); fetchTimesheets(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetTimesheetForm = () => {
        setTimesheetForm({ employeeId: '', taskId: '', projectId: '', entryDate: '', hoursWorked: 0, description: '', status: 'PENDING' });
        setEditingTimesheetId(null);
    };

    // ==================== PHASE 10: COMPANIES ====================
    const [companies, setCompanies] = useState([]);
    const [companyForm, setCompanyForm] = useState({
        companyName: '', companyCode: '', gstNo: '', panNo: '',
        address: '', city: '', state: '', pincode: '', country: 'India',
        phone: '', email: '', currency: 'INR', status: 'ACTIVE'
    });
    const [editingCompanyId, setEditingCompanyId] = useState(null);

    const fetchCompanies = async () => {
        try { const res = await axios.get('/companies'); setCompanies(Array.isArray(res.data) ? res.data : []); } catch (e) { setCompanies([]); }
    };

    const handleCompanySubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingCompanyId) {
                await axios.put(`/companies/${editingCompanyId}`, companyForm);
                setMessage({ text: 'Company updated!', type: 'success' });
            } else {
                await axios.post('/companies', companyForm);
                setMessage({ text: 'Company added!', type: 'success' });
            }
            resetCompanyForm();
            fetchCompanies();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteCompany = async (id) => {
        if (!window.confirm('Delete this company?')) return;
        try { await axios.delete(`/companies/${id}`); setMessage({ text: 'Company deleted!', type: 'success' }); fetchCompanies(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetCompanyForm = () => {
        setCompanyForm({ companyName: '', companyCode: '', gstNo: '', panNo: '', address: '', city: '', state: '', pincode: '', country: 'India', phone: '', email: '', currency: 'INR', status: 'ACTIVE' });
        setEditingCompanyId(null);
    };

    // ==================== PHASE 10: BUDGETS ====================
    const [budgets, setBudgets] = useState([]);
    const [budgetForm, setBudgetForm] = useState({
        companyId: '', budgetName: '', category: '',
        amount: 0, fiscalYear: '', status: 'ACTIVE'
    });
    const [editingBudgetId, setEditingBudgetId] = useState(null);

    const fetchBudgets = async () => {
        try { const res = await axios.get('/budgets'); setBudgets(Array.isArray(res.data) ? res.data : []); } catch (e) { setBudgets([]); }
    };

    const handleBudgetSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            if (editingBudgetId) {
                await axios.put(`/budgets/${editingBudgetId}`, budgetForm);
                setMessage({ text: 'Budget updated!', type: 'success' });
            } else {
                await axios.post('/budgets', budgetForm);
                setMessage({ text: 'Budget added!', type: 'success' });
            }
            resetBudgetForm();
            fetchBudgets();
            fetchAllData();
        } catch (error) {
            setMessage({ text: error.response?.data?.error || 'Error!', type: 'danger' });
        } finally { setLoading(false); }
    };

    const deleteBudget = async (id) => {
        if (!window.confirm('Delete this budget?')) return;
        try { await axios.delete(`/budgets/${id}`); setMessage({ text: 'Budget deleted!', type: 'success' }); fetchBudgets(); fetchAllData(); } catch (e) { setMessage({ text: 'Error deleting!', type: 'danger' }); }
    };

    const resetBudgetForm = () => {
        setBudgetForm({ companyId: '', budgetName: '', category: '', amount: 0, fiscalYear: '', status: 'ACTIVE' });
        setEditingBudgetId(null);
    };

    // ==================== FETCH ALL DATA ====================
    useEffect(() => {
        fetchAllData();
    }, []);

    const fetchAllData = async () => {
        await Promise.all([
            fetchCustomers(),
            fetchProducts(),
            fetchVendors(),
            fetchInvoices(),
            fetchPurchaseOrders(),
            fetchDashboardStats(),
            fetchSalesReport(),
            fetchPurchaseReport(),
            fetchGSTReport(),
            fetchStockReport(),
            fetchCustomerReport(),
            fetchVendorReport(),
            fetchTrialBalance(),
            fetchProfitLoss(),
            fetchEmployees(),
            fetchLeaves(),
            fetchBoms(),
            fetchProductionOrders(),
            fetchProjects(),
            fetchTasks(),
            fetchTimesheets(),
            fetchCompanies(),
            fetchBudgets()
        ]);
    };

    // ==================== EXPORT FUNCTIONS ====================
    const exportToCSV = (data, filename) => {
        if (!data || data.length === 0) {
            setMessage({ text: 'No data to export!', type: 'warning' });
            return;
        }
        const headers = Object.keys(data[0]);
        const csvRows = [];
        csvRows.push(headers.join(','));
        for (const row of data) {
            const values = headers.map(header => {
                const val = row[header] || '';
                return `"${String(val).replace(/"/g, '""')}"`;
            });
            csvRows.push(values.join(','));
        }
        const csvString = csvRows.join('\n');
        const blob = new Blob([csvString], { type: 'text/csv' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `${filename}.csv`;
        a.click();
        URL.revokeObjectURL(url);
        setMessage({ text: 'Exported successfully!', type: 'success' });
    };

    const exportToExcel = (data, filename) => {
        exportToCSV(data, filename);
    };

    // ==================== RENDER HELPERS ====================
    const renderReportCards = (title, value, color, icon) => (
        <div className="col-md-2 col-6 mb-2">
            <div className={`card bg-${color} text-white shadow-sm`}>
                <div className="card-body text-center py-2">
                    <h3>{icon}</h3>
                    <h5>{typeof value === 'number' ? value.toLocaleString() : value}</h5>
                    <small>{title}</small>
                </div>
            </div>
        </div>
    );

    const renderTable = (data, columns, actions) => {
        if (!data || data.length === 0) {
            return <div className="text-center text-muted py-3">No data available</div>;
        }
        return (
            <div className="table-responsive" style={{ maxHeight: '400px', overflow: 'auto' }}>
                <table className="table table-striped table-sm">
                    <thead>
                        <tr>
                            {columns.map(col => <th key={col.key}>{col.label}</th>)}
                            {actions && <th>Actions</th>}
                        </tr>
                    </thead>
                    <tbody>
                        {data.map((row, idx) => (
                            <tr key={idx}>
                                {columns.map(col => (
                                    <td key={col.key}>
                                        {col.format ? col.format(row[col.key]) : row[col.key]}
                                    </td>
                                ))}
                                {actions && (
                                    <td>
                                        {actions.map((action, i) => (
                                            <button key={i} className={`btn btn-${action.color} btn-sm me-1`} onClick={() => action.onClick(row)}>
                                                {action.icon}
                                            </button>
                                        ))}
                                    </td>
                                )}
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        );
    };

    // ==================== SIDEBARS ====================
    const LeftSidebar = () => (
        <div className={`position-fixed top-0 start-0 vh-100 bg-dark text-white transition-all duration-300 ease-in-out ${leftOpen ? 'width-220' : 'width-60'}`} 
             style={{ width: leftOpen ? '220px' : '60px', zIndex: 1040, paddingTop: '70px', overflow: 'hidden', boxShadow: '2px 0 15px rgba(0,0,0,0.3)' }}>
            <div className="d-flex flex-column h-100">
                {leftTabs.map((tab) => (
                    <button key={tab.id}
                        className={`btn w-100 text-start border-0 rounded-0 py-3 px-3 ${activeTab === tab.id ? 'bg-primary text-white' : 'text-white-50 hover-bg-primary-20'} transition-all duration-200`}
                        onClick={() => setActiveTab(tab.id)}
                        style={{ backgroundColor: activeTab === tab.id ? '#0d6efd' : 'transparent', fontSize: leftOpen ? '13px' : '11px' }}>
                        <span style={{ fontSize: '18px', display: 'inline-block', width: '28px' }}>{tab.icon}</span>
                        {leftOpen && <span className="ms-2">{tab.label}</span>}
                    </button>
                ))}
            </div>
        </div>
    );

    const RightSidebar = () => (
        <div className={`position-fixed top-0 end-0 vh-100 bg-dark text-white transition-all duration-300 ease-in-out ${rightOpen ? 'width-220' : 'width-60'}`}
             style={{ width: rightOpen ? '220px' : '60px', zIndex: 1040, paddingTop: '70px', overflow: 'hidden', boxShadow: '-2px 0 15px rgba(0,0,0,0.3)' }}>
            <div className="d-flex flex-column h-100">
                {rightTabs.map((tab) => (
                    <button key={tab.id}
                        className={`btn w-100 text-start border-0 rounded-0 py-3 px-3 ${activeTab === tab.id ? 'bg-primary text-white' : 'text-white-50 hover-bg-primary-20'} transition-all duration-200`}
                        onClick={() => setActiveTab(tab.id)}
                        style={{ backgroundColor: activeTab === tab.id ? '#0d6efd' : 'transparent', fontSize: rightOpen ? '13px' : '11px' }}>
                        <span style={{ fontSize: '18px', display: 'inline-block', width: '28px' }}>{tab.icon}</span>
                        {rightOpen && <span className="ms-2">{tab.label}</span>}
                    </button>
                ))}
            </div>
        </div>
    );

    // ==================== NAVBAR ====================
    const Navbar = () => (
        <nav className="navbar navbar-dark bg-dark fixed-top shadow-lg" style={{ zIndex: 1050, height: '60px' }}>
            <div className="container-fluid px-3">
                <div className="d-flex align-items-center">
                    <button className="btn btn-outline-light btn-sm me-2" onClick={toggleLeft} style={{ border: 'none', fontSize: '20px' }}>☰</button>
                    <span className="navbar-brand fw-bold text-warning" style={{ fontSize: '1.2rem' }}>🇮🇳 BHARAT ERP 2026</span>
                </div>
                <div className="text-center text-light d-none d-md-block">
                    <span className="fw-bold">📅 {formatDate(currentTime)}</span>
                    <span className="mx-2 text-warning">|</span>
                    <span className="fw-bold text-warning">🕐 {formatTime(currentTime)}</span>
                </div>
                <div className="d-flex align-items-center">
                    <span className="text-light me-2 d-none d-sm-block">👤 {CREATOR.name}</span>
                    <button className="btn btn-outline-light btn-sm" onClick={toggleRight} style={{ border: 'none', fontSize: '20px' }}>☰</button>
                </div>
            </div>
        </nav>
    );

    // ==================== FOOTER ====================
    const Footer = () => (
        <footer className="bg-dark text-white-50 py-3 mt-auto" style={{ borderTop: '1px solid #444' }}>
            <div className="container-fluid px-4">
                <div className="row align-items-center">
                    <div className="col-md-4 text-center text-md-start">
                        <small>🇮🇳 <strong className="text-warning">BHARAT ERP 2026</strong></small><br />
                        <small>Created by: <strong className="text-primary">{CREATOR.name}</strong></small>
                    </div>
                    <div className="col-md-4 text-center">
                        <small>📞 {CREATOR.phone} | 📧 {CREATOR.email}</small><br />
                        <small>📍 {CREATOR.location}</small>
                    </div>
                    <div className="col-md-4 text-center text-md-end">
                        <a href={CREATOR.linkedin} target="_blank" rel="noopener noreferrer" className="text-info text-decoration-none me-2">🔗 LinkedIn</a>
                        <a href={CREATOR.github} target="_blank" rel="noopener noreferrer" className="text-light text-decoration-none me-2">🐙 GitHub</a>
                        <a href={CREATOR.instagram} target="_blank" rel="noopener noreferrer" className="text-danger text-decoration-none">📸 Instagram</a><br />
                        <small className="text-muted">© 2026 BHARAT ERP | Made in India 🇮🇳</small>
                    </div>
                </div>
            </div>
        </footer>
    );

    // ==================== RENDER CONTENT ====================
    const renderContent = () => {
        // DASHBOARD
        if (activeTab === 'dashboard') {
            return (
                <div>
                    <div className="row g-2 mb-4">
                        {renderReportCards('Customers', dashboardStats.totalCustomers || 0, 'primary', '👥')}
                        {renderReportCards('Products', dashboardStats.totalProducts || 0, 'success', '📦')}
                        {renderReportCards('Vendors', dashboardStats.totalVendors || 0, 'warning', '🏢')}
                        {renderReportCards('Invoices', dashboardStats.invoiceCount || 0, 'info', '📄')}
                        {renderReportCards('Employees', employees.length || 0, 'secondary', '👨‍💼')}
                        {renderReportCards('Projects', projects.length || 0, 'dark', '📊')}
                    </div>
                    <div className="row">
                        <div className="col-md-6">
                            <div className="card shadow-sm">
                                <div className="card-header bg-primary text-white">📈 Monthly Sales Trend</div>
                                <div className="card-body" style={{ height: '300px' }}>
                                    <ResponsiveContainer width="100%" height="100%">
                                        <AreaChart data={dashboardStats.monthlySales || []}>
                                            <CartesianGrid strokeDasharray="3 3" />
                                            <XAxis dataKey="month" />
                                            <YAxis />
                                            <Tooltip formatter={(value) => `₹${value.toLocaleString()}`} />
                                            <Area type="monotone" dataKey="sales" stroke="#e67e22" fill="#f39c12" />
                                        </AreaChart>
                                    </ResponsiveContainer>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="card shadow-sm">
                                <div className="card-header bg-success text-white">📊 AI Summary</div>
                                <div className="card-body">
                                    <div className="row text-center">
                                        <div className="col-4 border-end py-3"><h5>Agents</h5><h3 className="text-success">{agentResults.length || 0}</h3></div>
                                        <div className="col-4 border-end py-3"><h5>Anomalies</h5><h3 className="text-danger">{anomalies.length || 0}</h3></div>
                                        <div className="col-4 py-3"><h5>Employees</h5><h3 className="text-primary">{employees.length || 0}</h3></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // CUSTOMERS
        if (activeTab === 'customers') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-primary text-white"><h5>{editingCustomerId ? '✏️ Edit Customer' : '➕ Add Customer'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleCustomerSubmit}>
                                    <div className="row g-2">
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Code*" value={customerForm.code} onChange={(e) => setCustomerForm({...customerForm, code: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Name*" value={customerForm.name} onChange={(e) => setCustomerForm({...customerForm, name: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="GSTIN" value={customerForm.gstin} onChange={(e) => setCustomerForm({...customerForm, gstin: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Phone" value={customerForm.phone} onChange={(e) => setCustomerForm({...customerForm, phone: e.target.value})} /></div>
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Email" value={customerForm.email} onChange={(e) => setCustomerForm({...customerForm, email: e.target.value})} /></div>
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Address" value={customerForm.address} onChange={(e) => setCustomerForm({...customerForm, address: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="City" value={customerForm.city} onChange={(e) => setCustomerForm({...customerForm, city: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="State" value={customerForm.state} onChange={(e) => setCustomerForm({...customerForm, state: e.target.value})} /></div>
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Credit Limit" type="number" value={customerForm.creditLimit} onChange={(e) => setCustomerForm({...customerForm, creditLimit: parseFloat(e.target.value)})} /></div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-primary btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingCustomerId ? 'Update' : 'Add'}</button>
                                            {editingCustomerId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetCustomerForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-primary text-white"><h5>👥 Customers List ({customers.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Code</th><th>Name</th><th>GSTIN</th><th>Phone</th><th>City</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {customers.map(c => (
                                            <tr key={c.id}>
                                                <td><strong>{c.code}</strong></td>
                                                <td>{c.name}</td>
                                                <td>{c.gstin}</td>
                                                <td>{c.phone}</td>
                                                <td>{c.city}</td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingCustomerId(c.id); setCustomerForm(c); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteCustomer(c.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // PRODUCTS
        if (activeTab === 'products') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-success text-white"><h5>{editingProductId ? '✏️ Edit Product' : '➕ Add Product'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleProductSubmit}>
                                    <div className="row g-2">
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Code*" value={productForm.code} onChange={(e) => setProductForm({...productForm, code: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Name*" value={productForm.name} onChange={(e) => setProductForm({...productForm, name: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="HSN" value={productForm.hsn} onChange={(e) => setProductForm({...productForm, hsn: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Unit" value={productForm.unit} onChange={(e) => setProductForm({...productForm, unit: e.target.value})} /></div>
                                        <div className="col-4"><input className="form-control form-control-sm" placeholder="GST %" type="number" value={productForm.gstRate} onChange={(e) => setProductForm({...productForm, gstRate: parseFloat(e.target.value)})} /></div>
                                        <div className="col-4"><input className="form-control form-control-sm" placeholder="Purchase Price" type="number" value={productForm.purchasePrice} onChange={(e) => setProductForm({...productForm, purchasePrice: parseFloat(e.target.value)})} /></div>
                                        <div className="col-4"><input className="form-control form-control-sm" placeholder="Selling Price" type="number" value={productForm.sellingPrice} onChange={(e) => setProductForm({...productForm, sellingPrice: parseFloat(e.target.value)})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Stock" type="number" value={productForm.stock} onChange={(e) => setProductForm({...productForm, stock: parseFloat(e.target.value)})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Min Stock" type="number" value={productForm.minStock} onChange={(e) => setProductForm({...productForm, minStock: parseFloat(e.target.value)})} /></div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-success btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingProductId ? 'Update' : 'Add'}</button>
                                            {editingProductId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetProductForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-success text-white"><h5>📦 Products List ({products.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Code</th><th>Name</th><th>HSN</th><th>GST%</th><th>Price</th><th>Stock</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {products.map(p => (
                                            <tr key={p.id}>
                                                <td><strong>{p.code}</strong></td>
                                                <td>{p.name}</td>
                                                <td>{p.hsn}</td>
                                                <td>{p.gstRate}%</td>
                                                <td>₹{p.sellingPrice}</td>
                                                <td>{p.stock}</td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingProductId(p.id); setProductForm(p); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteProduct(p.id)}>🗑️</button>
                                                    <button className="btn btn-info btn-sm ms-1" onClick={() => { addInvoiceItem(p); setActiveTab('invoices'); setShowModal(true); setModalType('invoice'); }}>➕ Inv</button>
                                                    <button className="btn btn-secondary btn-sm ms-1" onClick={() => { addPOItem(p); setActiveTab('purchase-orders'); setShowModal(true); setModalType('po'); }}>📋 PO</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // VENDORS
        if (activeTab === 'vendors') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-warning text-dark"><h5>{editingVendorId ? '✏️ Edit Vendor' : '➕ Add Vendor'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleVendorSubmit}>
                                    <div className="row g-2">
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Code*" value={vendorForm.code} onChange={(e) => setVendorForm({...vendorForm, code: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Name*" value={vendorForm.name} onChange={(e) => setVendorForm({...vendorForm, name: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="GSTIN" value={vendorForm.gstin} onChange={(e) => setVendorForm({...vendorForm, gstin: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Phone" value={vendorForm.phone} onChange={(e) => setVendorForm({...vendorForm, phone: e.target.value})} /></div>
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Email" value={vendorForm.email} onChange={(e) => setVendorForm({...vendorForm, email: e.target.value})} /></div>
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Address" value={vendorForm.address} onChange={(e) => setVendorForm({...vendorForm, address: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="City" value={vendorForm.city} onChange={(e) => setVendorForm({...vendorForm, city: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="State" value={vendorForm.state} onChange={(e) => setVendorForm({...vendorForm, state: e.target.value})} /></div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-warning btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingVendorId ? 'Update' : 'Add'}</button>
                                            {editingVendorId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetVendorForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-warning text-dark"><h5>🏢 Vendors List ({vendors.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Code</th><th>Name</th><th>GSTIN</th><th>Phone</th><th>City</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {vendors.map(v => (
                                            <tr key={v.id}>
                                                <td><strong>{v.code}</strong></td>
                                                <td>{v.name}</td>
                                                <td>{v.gstin}</td>
                                                <td>{v.phone}</td>
                                                <td>{v.city}</td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingVendorId(v.id); setVendorForm(v); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteVendor(v.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // INVOICES
        if (activeTab === 'invoices') {
            return (
                <div>
                    <button className="btn btn-primary mb-3" onClick={() => { resetInvoiceForm(); setShowModal(true); setModalType('invoice'); }}>➕ Create Invoice</button>
                    <div className="card shadow-sm">
                        <div className="card-header bg-primary text-white"><h5>📄 Invoices List ({invoices.length})</h5></div>
                        <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                            <table className="table table-striped">
                                <thead><tr><th>Number</th><th>Date</th><th>Customer</th><th>Total</th><th>GST</th><th>Grand Total</th><th>Status</th><th>Actions</th></tr></thead>
                                <tbody>
                                    {invoices.map(inv => (
                                        <tr key={inv.id}>
                                            <td><strong>{inv.invoiceNumber}</strong></td>
                                            <td>{inv.invoiceDate}</td>
                                            <td>{inv.customerName}</td>
                                            <td>₹{inv.totalAmount}</td>
                                            <td>₹{inv.totalGst}</td>
                                            <td><strong>₹{inv.grandTotal}</strong></td>
                                            <td><span className="badge bg-info">{inv.status}</span></td>
                                            <td><button className="btn btn-danger btn-sm" onClick={() => deleteInvoice(inv.id)}>🗑️</button></td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            );
        }

        // PURCHASE ORDERS
        if (activeTab === 'purchase-orders') {
            return (
                <div>
                    <button className="btn btn-success mb-3" onClick={() => { resetPOForm(); setShowModal(true); setModalType('po'); }}>➕ Create Purchase Order</button>
                    <div className="card shadow-sm">
                        <div className="card-header bg-success text-white"><h5>📋 Purchase Orders List ({purchaseOrders.length})</h5></div>
                        <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                            <table className="table table-striped">
                                <thead><tr><th>PO No</th><th>Date</th><th>Vendor</th><th>Total</th><th>GST</th><th>Grand Total</th><th>Status</th><th>Actions</th></tr></thead>
                                <tbody>
                                    {purchaseOrders.map(po => (
                                        <tr key={po.id}>
                                            <td><strong>{po.poNumber}</strong></td>
                                            <td>{po.poDate}</td>
                                            <td>{po.vendorName}</td>
                                            <td>₹{po.totalAmount}</td>
                                            <td>₹{po.totalGst}</td>
                                            <td><strong>₹{po.grandTotal}</strong></td>
                                            <td><span className="badge bg-warning">{po.status}</span></td>
                                            <td><button className="btn btn-danger btn-sm" onClick={() => deletePO(po.id)}>🗑️</button></td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            );
        }

        // ==================== EMPLOYEES ====================
        if (activeTab === 'hr-employees') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-info text-white"><h5>{editingEmployeeId ? '✏️ Edit Employee' : '➕ Add Employee'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleEmployeeSubmit}>
                                    <div className="row g-2">
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="First Name*" value={employeeForm.firstName} onChange={(e) => setEmployeeForm({...employeeForm, firstName: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Last Name" value={employeeForm.lastName} onChange={(e) => setEmployeeForm({...employeeForm, lastName: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Email*" type="email" value={employeeForm.email} onChange={(e) => setEmployeeForm({...employeeForm, email: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Phone" value={employeeForm.phone} onChange={(e) => setEmployeeForm({...employeeForm, phone: e.target.value})} /></div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={employeeForm.department} onChange={(e) => setEmployeeForm({...employeeForm, department: e.target.value})}>
                                                <option value="">Department</option>
                                                <option value="IT">IT</option>
                                                <option value="HR">HR</option>
                                                <option value="Finance">Finance</option>
                                                <option value="Sales">Sales</option>
                                                <option value="Production">Production</option>
                                            </select>
                                        </div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Designation" value={employeeForm.designation} onChange={(e) => setEmployeeForm({...employeeForm, designation: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="date" placeholder="Joining Date" value={employeeForm.joiningDate} onChange={(e) => setEmployeeForm({...employeeForm, joiningDate: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="number" placeholder="Salary" value={employeeForm.salary} onChange={(e) => setEmployeeForm({...employeeForm, salary: parseFloat(e.target.value)})} /></div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={employeeForm.gender} onChange={(e) => setEmployeeForm({...employeeForm, gender: e.target.value})}>
                                                <option value="">Gender</option>
                                                <option value="MALE">Male</option>
                                                <option value="FEMALE">Female</option>
                                            </select>
                                        </div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="date" placeholder="DOB" value={employeeForm.dateOfBirth} onChange={(e) => setEmployeeForm({...employeeForm, dateOfBirth: e.target.value})} /></div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-info btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingEmployeeId ? 'Update' : 'Add'}</button>
                                            {editingEmployeeId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetEmployeeForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-info text-white"><h5>👨‍💼 Employees List ({employees.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>ID</th><th>Name</th><th>Email</th><th>Department</th><th>Designation</th><th>Salary</th><th>Status</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {employees.map(e => (
                                            <tr key={e.id}>
                                                <td><strong>{e.employeeId}</strong></td>
                                                <td>{e.firstName} {e.lastName}</td>
                                                <td>{e.email}</td>
                                                <td>{e.department}</td>
                                                <td>{e.designation}</td>
                                                <td>₹{e.salary}</td>
                                                <td><span className={`badge bg-${e.status === 'ACTIVE' ? 'success' : 'danger'}`}>{e.status}</span></td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingEmployeeId(e.id); setEmployeeForm(e); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteEmployee(e.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // LEAVES
        if (activeTab === 'hr-leaves') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-warning text-dark"><h5>{editingLeaveId ? '✏️ Edit Leave' : '➕ Add Leave'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleLeaveSubmit}>
                                    <div className="row g-2">
                                        <div className="col-12">
                                            <select className="form-select form-select-sm" value={leaveForm.employeeId} onChange={(e) => setLeaveForm({...leaveForm, employeeId: e.target.value})} required>
                                                <option value="">Select Employee</option>
                                                {employees.map(e => <option key={e.id} value={e.id}>{e.firstName} {e.lastName}</option>)}
                                            </select>
                                        </div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={leaveForm.leaveType} onChange={(e) => setLeaveForm({...leaveForm, leaveType: e.target.value})}>
                                                <option value="CASUAL">Casual</option>
                                                <option value="SICK">Sick</option>
                                                <option value="EARNED">Earned</option>
                                                <option value="OTHER">Other</option>
                                            </select>
                                        </div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="date" placeholder="Start Date" value={leaveForm.startDate} onChange={(e) => setLeaveForm({...leaveForm, startDate: e.target.value})} required /></div>
                                        <div className="col-12"><input className="form-control form-control-sm" type="date" placeholder="End Date" value={leaveForm.endDate} onChange={(e) => setLeaveForm({...leaveForm, endDate: e.target.value})} required /></div>
                                        <div className="col-12"><textarea className="form-control form-control-sm" placeholder="Reason" rows="2" value={leaveForm.reason} onChange={(e) => setLeaveForm({...leaveForm, reason: e.target.value})} /></div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-warning btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingLeaveId ? 'Update' : 'Add'}</button>
                                            {editingLeaveId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetLeaveForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-warning text-dark"><h5>📅 Leaves List ({leaves.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Employee</th><th>Type</th><th>Start</th><th>End</th><th>Days</th><th>Status</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {leaves.map(l => (
                                            <tr key={l.id}>
                                                <td>{l.employeeName || l.employeeId}</td>
                                                <td>{l.leaveType}</td>
                                                <td>{l.startDate}</td>
                                                <td>{l.endDate}</td>
                                                <td>{l.days}</td>
                                                <td><span className={`badge bg-${l.status === 'APPROVED' ? 'success' : l.status === 'REJECTED' ? 'danger' : 'warning'}`}>{l.status}</span></td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingLeaveId(l.id); setLeaveForm(l); }}>✏️</button>
                                                    <button className="btn btn-success btn-sm me-1" onClick={() => approveLeave(l.id)}>✅</button>
                                                    <button className="btn btn-danger btn-sm me-1" onClick={() => rejectLeave(l.id)}>❌</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteLeave(l.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // BOM
        if (activeTab === 'mfg-bom') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-success text-white"><h5>{editingBomId ? '✏️ Edit BOM' : '➕ Add BOM'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleBomSubmit}>
                                    <div className="row g-2">
                                        <div className="col-12">
                                            <select className="form-select form-select-sm" value={bomForm.productId} onChange={(e) => setBomForm({...bomForm, productId: e.target.value})} required>
                                                <option value="">Select Product</option>
                                                {products.map(p => <option key={p.id} value={p.id}>{p.code} - {p.name}</option>)}
                                            </select>
                                        </div>
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="BOM Name*" value={bomForm.bomName} onChange={(e) => setBomForm({...bomForm, bomName: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Version" value={bomForm.version} onChange={(e) => setBomForm({...bomForm, version: e.target.value})} /></div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={bomForm.status} onChange={(e) => setBomForm({...bomForm, status: e.target.value})}>
                                                <option value="ACTIVE">Active</option>
                                                <option value="INACTIVE">Inactive</option>
                                                <option value="DRAFT">Draft</option>
                                            </select>
                                        </div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-success btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingBomId ? 'Update' : 'Add'}</button>
                                            {editingBomId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetBomForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-success text-white"><h5>📋 BOM List ({boms.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Product</th><th>BOM Name</th><th>Version</th><th>Status</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {boms.map(b => (
                                            <tr key={b.id}>
                                                <td>{b.productId}</td>
                                                <td>{b.bomName}</td>
                                                <td>{b.version}</td>
                                                <td><span className={`badge bg-${b.status === 'ACTIVE' ? 'success' : 'secondary'}`}>{b.status}</span></td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingBomId(b.id); setBomForm(b); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteBom(b.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // PRODUCTION ORDERS
        if (activeTab === 'mfg-production') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-danger text-white"><h5>{editingProductionId ? '✏️ Edit Production' : '➕ Add Production Order'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleProductionSubmit}>
                                    <div className="row g-2">
                                        <div className="col-12">
                                            <select className="form-select form-select-sm" value={productionForm.productId} onChange={(e) => setProductionForm({...productionForm, productId: e.target.value})} required>
                                                <option value="">Select Product</option>
                                                {products.map(p => <option key={p.id} value={p.id}>{p.code} - {p.name}</option>)}
                                            </select>
                                        </div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="number" placeholder="Quantity" value={productionForm.quantity} onChange={(e) => setProductionForm({...productionForm, quantity: parseFloat(e.target.value)})} required /></div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={productionForm.priority} onChange={(e) => setProductionForm({...productionForm, priority: e.target.value})}>
                                                <option value="LOW">Low</option>
                                                <option value="MEDIUM">Medium</option>
                                                <option value="HIGH">High</option>
                                                <option value="URGENT">Urgent</option>
                                            </select>
                                        </div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="date" placeholder="Start Date" value={productionForm.startDate} onChange={(e) => setProductionForm({...productionForm, startDate: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="date" placeholder="End Date" value={productionForm.endDate} onChange={(e) => setProductionForm({...productionForm, endDate: e.target.value})} /></div>
                                        <div className="col-12">
                                            <select className="form-select form-select-sm" value={productionForm.status} onChange={(e) => setProductionForm({...productionForm, status: e.target.value})}>
                                                <option value="PLANNED">Planned</option>
                                                <option value="IN_PROGRESS">In Progress</option>
                                                <option value="COMPLETED">Completed</option>
                                                <option value="CANCELLED">Cancelled</option>
                                            </select>
                                        </div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-danger btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingProductionId ? 'Update' : 'Add'}</button>
                                            {editingProductionId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetProductionForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-danger text-white"><h5>🏭 Production Orders ({productionOrders.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Product</th><th>Qty</th><th>Start</th><th>End</th><th>Status</th><th>Priority</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {productionOrders.map(p => (
                                            <tr key={p.id}>
                                                <td>{p.productId}</td>
                                                <td>{p.quantity}</td>
                                                <td>{p.startDate}</td>
                                                <td>{p.endDate}</td>
                                                <td><span className={`badge bg-${p.status === 'COMPLETED' ? 'success' : p.status === 'IN_PROGRESS' ? 'warning' : 'secondary'}`}>{p.status}</span></td>
                                                <td><span className={`badge bg-${p.priority === 'HIGH' ? 'danger' : p.priority === 'URGENT' ? 'dark' : 'info'}`}>{p.priority}</span></td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingProductionId(p.id); setProductionForm(p); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteProduction(p.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // PROJECTS
        if (activeTab === 'projects') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-primary text-white"><h5>{editingProjectId ? '✏️ Edit Project' : '➕ Add Project'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleProjectSubmit}>
                                    <div className="row g-2">
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Project Name*" value={projectForm.projectName} onChange={(e) => setProjectForm({...projectForm, projectName: e.target.value})} required /></div>
                                        <div className="col-12"><textarea className="form-control form-control-sm" placeholder="Description" rows="2" value={projectForm.description} onChange={(e) => setProjectForm({...projectForm, description: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Client Name" value={projectForm.clientName} onChange={(e) => setProjectForm({...projectForm, clientName: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="number" placeholder="Budget" value={projectForm.budget} onChange={(e) => setProjectForm({...projectForm, budget: parseFloat(e.target.value)})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="date" placeholder="Start Date" value={projectForm.startDate} onChange={(e) => setProjectForm({...projectForm, startDate: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="date" placeholder="End Date" value={projectForm.endDate} onChange={(e) => setProjectForm({...projectForm, endDate: e.target.value})} /></div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={projectForm.status} onChange={(e) => setProjectForm({...projectForm, status: e.target.value})}>
                                                <option value="PLANNED">Planned</option>
                                                <option value="ACTIVE">Active</option>
                                                <option value="ON_HOLD">On Hold</option>
                                                <option value="COMPLETED">Completed</option>
                                            </select>
                                        </div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={projectForm.priority} onChange={(e) => setProjectForm({...projectForm, priority: e.target.value})}>
                                                <option value="LOW">Low</option>
                                                <option value="MEDIUM">Medium</option>
                                                <option value="HIGH">High</option>
                                            </select>
                                        </div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-primary btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingProjectId ? 'Update' : 'Add'}</button>
                                            {editingProjectId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetProjectForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-primary text-white"><h5>📊 Projects List ({projects.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Code</th><th>Name</th><th>Client</th><th>Budget</th><th>Status</th><th>Priority</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {projects.map(p => (
                                            <tr key={p.id}>
                                                <td><strong>{p.projectCode}</strong></td>
                                                <td>{p.projectName}</td>
                                                <td>{p.clientName}</td>
                                                <td>₹{p.budget}</td>
                                                <td><span className={`badge bg-${p.status === 'COMPLETED' ? 'success' : p.status === 'ACTIVE' ? 'primary' : 'secondary'}`}>{p.status}</span></td>
                                                <td><span className={`badge bg-${p.priority === 'HIGH' ? 'danger' : 'info'}`}>{p.priority}</span></td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingProjectId(p.id); setProjectForm(p); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteProject(p.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // TASKS
        if (activeTab === 'tasks') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-success text-white"><h5>{editingTaskId ? '✏️ Edit Task' : '➕ Add Task'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleTaskSubmit}>
                                    <div className="row g-2">
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Task Name*" value={taskForm.taskName} onChange={(e) => setTaskForm({...taskForm, taskName: e.target.value})} required /></div>
                                        <div className="col-12">
                                            <select className="form-select form-select-sm" value={taskForm.projectId} onChange={(e) => setTaskForm({...taskForm, projectId: e.target.value})} required>
                                                <option value="">Select Project</option>
                                                {projects.map(p => <option key={p.id} value={p.id}>{p.projectName}</option>)}
                                            </select>
                                        </div>
                                        <div className="col-12">
                                            <select className="form-select form-select-sm" value={taskForm.assignedTo} onChange={(e) => setTaskForm({...taskForm, assignedTo: e.target.value})}>
                                                <option value="">Assign To</option>
                                                {employees.map(e => <option key={e.id} value={e.id}>{e.firstName} {e.lastName}</option>)}
                                            </select>
                                        </div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="date" placeholder="Start Date" value={taskForm.startDate} onChange={(e) => setTaskForm({...taskForm, startDate: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="date" placeholder="Due Date" value={taskForm.dueDate} onChange={(e) => setTaskForm({...taskForm, dueDate: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="number" placeholder="Est. Hours" value={taskForm.estimatedHours} onChange={(e) => setTaskForm({...taskForm, estimatedHours: parseFloat(e.target.value)})} /></div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={taskForm.status} onChange={(e) => setTaskForm({...taskForm, status: e.target.value})}>
                                                <option value="PENDING">Pending</option>
                                                <option value="IN_PROGRESS">In Progress</option>
                                                <option value="COMPLETED">Completed</option>
                                            </select>
                                        </div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-success btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingTaskId ? 'Update' : 'Add'}</button>
                                            {editingTaskId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetTaskForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-success text-white"><h5>✅ Tasks List ({tasks.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Task</th><th>Project</th><th>Assigned To</th><th>Due Date</th><th>Status</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {tasks.map(t => (
                                            <tr key={t.id}>
                                                <td>{t.taskName}</td>
                                                <td>{t.projectId}</td>
                                                <td>{t.assignedTo}</td>
                                                <td>{t.dueDate}</td>
                                                <td><span className={`badge bg-${t.status === 'COMPLETED' ? 'success' : t.status === 'IN_PROGRESS' ? 'warning' : 'secondary'}`}>{t.status}</span></td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingTaskId(t.id); setTaskForm(t); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteTask(t.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // TIMESHEETS
        if (activeTab === 'timesheets') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-info text-white"><h5>{editingTimesheetId ? '✏️ Edit Timesheet' : '➕ Add Timesheet'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleTimesheetSubmit}>
                                    <div className="row g-2">
                                        <div className="col-12">
                                            <select className="form-select form-select-sm" value={timesheetForm.employeeId} onChange={(e) => setTimesheetForm({...timesheetForm, employeeId: e.target.value})} required>
                                                <option value="">Select Employee</option>
                                                {employees.map(e => <option key={e.id} value={e.id}>{e.firstName} {e.lastName}</option>)}
                                            </select>
                                        </div>
                                        <div className="col-12">
                                            <select className="form-select form-select-sm" value={timesheetForm.taskId} onChange={(e) => setTimesheetForm({...timesheetForm, taskId: e.target.value})}>
                                                <option value="">Select Task</option>
                                                {tasks.map(t => <option key={t.id} value={t.id}>{t.taskName}</option>)}
                                            </select>
                                        </div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="date" placeholder="Date" value={timesheetForm.entryDate} onChange={(e) => setTimesheetForm({...timesheetForm, entryDate: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="number" placeholder="Hours Worked" value={timesheetForm.hoursWorked} onChange={(e) => setTimesheetForm({...timesheetForm, hoursWorked: parseFloat(e.target.value)})} required /></div>
                                        <div className="col-12"><textarea className="form-control form-control-sm" placeholder="Description" rows="2" value={timesheetForm.description} onChange={(e) => setTimesheetForm({...timesheetForm, description: e.target.value})} /></div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-info btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingTimesheetId ? 'Update' : 'Add'}</button>
                                            {editingTimesheetId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetTimesheetForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-info text-white"><h5>⏱️ Timesheets ({timesheets.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Employee</th><th>Task</th><th>Date</th><th>Hours</th><th>Status</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {timesheets.map(t => (
                                            <tr key={t.id}>
                                                <td>{t.employeeId}</td>
                                                <td>{t.taskId}</td>
                                                <td>{t.entryDate}</td>
                                                <td>{t.hoursWorked}</td>
                                                <td><span className={`badge bg-${t.status === 'APPROVED' ? 'success' : 'warning'}`}>{t.status}</span></td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingTimesheetId(t.id); setTimesheetForm(t); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteTimesheet(t.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // COMPANIES
        if (activeTab === 'companies') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-dark text-white"><h5>{editingCompanyId ? '✏️ Edit Company' : '➕ Add Company'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleCompanySubmit}>
                                    <div className="row g-2">
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Company Name*" value={companyForm.companyName} onChange={(e) => setCompanyForm({...companyForm, companyName: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Company Code*" value={companyForm.companyCode} onChange={(e) => setCompanyForm({...companyForm, companyCode: e.target.value})} required /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="GST No" value={companyForm.gstNo} onChange={(e) => setCompanyForm({...companyForm, gstNo: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="PAN No" value={companyForm.panNo} onChange={(e) => setCompanyForm({...companyForm, panNo: e.target.value})} /></div>
                                        <div className="col-6"><input className="form-control form-control-sm" placeholder="Phone" value={companyForm.phone} onChange={(e) => setCompanyForm({...companyForm, phone: e.target.value})} /></div>
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Email" value={companyForm.email} onChange={(e) => setCompanyForm({...companyForm, email: e.target.value})} /></div>
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Address" value={companyForm.address} onChange={(e) => setCompanyForm({...companyForm, address: e.target.value})} /></div>
                                        <div className="col-4"><input className="form-control form-control-sm" placeholder="City" value={companyForm.city} onChange={(e) => setCompanyForm({...companyForm, city: e.target.value})} /></div>
                                        <div className="col-4"><input className="form-control form-control-sm" placeholder="State" value={companyForm.state} onChange={(e) => setCompanyForm({...companyForm, state: e.target.value})} /></div>
                                        <div className="col-4"><input className="form-control form-control-sm" placeholder="Pincode" value={companyForm.pincode} onChange={(e) => setCompanyForm({...companyForm, pincode: e.target.value})} /></div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={companyForm.currency} onChange={(e) => setCompanyForm({...companyForm, currency: e.target.value})}>
                                                <option value="INR">INR</option>
                                                <option value="USD">USD</option>
                                                <option value="EUR">EUR</option>
                                                <option value="GBP">GBP</option>
                                            </select>
                                        </div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={companyForm.status} onChange={(e) => setCompanyForm({...companyForm, status: e.target.value})}>
                                                <option value="ACTIVE">Active</option>
                                                <option value="INACTIVE">Inactive</option>
                                            </select>
                                        </div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-dark btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingCompanyId ? 'Update' : 'Add'}</button>
                                            {editingCompanyId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetCompanyForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-dark text-white"><h5>🏢 Companies ({companies.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Code</th><th>Name</th><th>GST</th><th>City</th><th>Currency</th><th>Status</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {companies.map(c => (
                                            <tr key={c.id}>
                                                <td><strong>{c.companyCode}</strong></td>
                                                <td>{c.companyName}</td>
                                                <td>{c.gstNo}</td>
                                                <td>{c.city}</td>
                                                <td>{c.currency}</td>
                                                <td><span className={`badge bg-${c.status === 'ACTIVE' ? 'success' : 'danger'}`}>{c.status}</span></td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingCompanyId(c.id); setCompanyForm(c); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteCompany(c.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // BUDGETS
        if (activeTab === 'budgets') {
            return (
                <div className="row">
                    <div className="col-md-4">
                        <div className="card shadow-sm">
                            <div className="card-header bg-warning text-dark"><h5>{editingBudgetId ? '✏️ Edit Budget' : '➕ Add Budget'}</h5></div>
                            <div className="card-body">
                                <form onSubmit={handleBudgetSubmit}>
                                    <div className="row g-2">
                                        <div className="col-12">
                                            <select className="form-select form-select-sm" value={budgetForm.companyId} onChange={(e) => setBudgetForm({...budgetForm, companyId: e.target.value})} required>
                                                <option value="">Select Company</option>
                                                {companies.map(c => <option key={c.id} value={c.id}>{c.companyName}</option>)}
                                            </select>
                                        </div>
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Budget Name*" value={budgetForm.budgetName} onChange={(e) => setBudgetForm({...budgetForm, budgetName: e.target.value})} required /></div>
                                        <div className="col-6">
                                            <select className="form-select form-select-sm" value={budgetForm.category} onChange={(e) => setBudgetForm({...budgetForm, category: e.target.value})}>
                                                <option value="">Category</option>
                                                <option value="OPERATIONAL">Operational</option>
                                                <option value="CAPITAL">Capital</option>
                                                <option value="PROJECT">Project</option>
                                                <option value="MARKETING">Marketing</option>
                                                <option value="HR">HR</option>
                                            </select>
                                        </div>
                                        <div className="col-6"><input className="form-control form-control-sm" type="number" placeholder="Amount" value={budgetForm.amount} onChange={(e) => setBudgetForm({...budgetForm, amount: parseFloat(e.target.value)})} required /></div>
                                        <div className="col-12"><input className="form-control form-control-sm" placeholder="Fiscal Year (2025-26)" value={budgetForm.fiscalYear} onChange={(e) => setBudgetForm({...budgetForm, fiscalYear: e.target.value})} /></div>
                                        <div className="col-12">
                                            <select className="form-select form-select-sm" value={budgetForm.status} onChange={(e) => setBudgetForm({...budgetForm, status: e.target.value})}>
                                                <option value="ACTIVE">Active</option>
                                                <option value="INACTIVE">Inactive</option>
                                                <option value="COMPLETED">Completed</option>
                                            </select>
                                        </div>
                                        <div className="col-12">
                                            <button type="submit" className="btn btn-warning btn-sm w-100" disabled={loading}>{loading ? 'Saving...' : editingBudgetId ? 'Update' : 'Add'}</button>
                                            {editingBudgetId && <button type="button" className="btn btn-secondary btn-sm w-100 mt-1" onClick={resetBudgetForm}>Cancel</button>}
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <div className="card shadow-sm">
                            <div className="card-header bg-warning text-dark"><h5>💰 Budgets ({budgets.length})</h5></div>
                            <div className="card-body" style={{ maxHeight: '500px', overflow: 'auto' }}>
                                <table className="table table-striped table-sm">
                                    <thead><tr><th>Name</th><th>Company</th><th>Category</th><th>Amount</th><th>Fiscal Year</th><th>Status</th><th>Actions</th></tr></thead>
                                    <tbody>
                                        {budgets.map(b => (
                                            <tr key={b.id}>
                                                <td>{b.budgetName}</td>
                                                <td>{b.companyId}</td>
                                                <td>{b.category}</td>
                                                <td>₹{b.amount}</td>
                                                <td>{b.fiscalYear}</td>
                                                <td><span className={`badge bg-${b.status === 'ACTIVE' ? 'success' : 'secondary'}`}>{b.status}</span></td>
                                                <td>
                                                    <button className="btn btn-warning btn-sm me-1" onClick={() => { setEditingBudgetId(b.id); setBudgetForm(b); }}>✏️</button>
                                                    <button className="btn btn-danger btn-sm" onClick={() => deleteBudget(b.id)}>🗑️</button>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // ==================== REPORTS ====================
        if (activeTab === 'sales-report') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-primary text-white d-flex justify-content-between align-items-center">
                        <h5>📈 Sales Report</h5>
                        <div>
                            <button className="btn btn-light btn-sm me-2" onClick={() => exportToExcel(salesReport.invoices || [], 'Sales_Report')}>📊 Excel</button>
                            <button className="btn btn-light btn-sm" onClick={() => exportToCSV(salesReport.invoices || [], 'Sales_Report')}>📄 CSV</button>
                        </div>
                    </div>
                    <div className="card-body">
                        <div className="row g-3 mb-3">
                            <div className="col-md-3"><div className="card bg-primary text-white"><div className="card-body text-center"><h6>Total Invoices</h6><h3>{salesReport.totalInvoices || 0}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-success text-white"><div className="card-body text-center"><h6>Taxable Amount</h6><h3>₹{(salesReport.totalTaxable || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-warning text-dark"><div className="card-body text-center"><h6>Total GST</h6><h3>₹{(salesReport.totalGst || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-danger text-white"><div className="card-body text-center"><h6>Grand Total</h6><h3>₹{(salesReport.totalGrand || 0).toLocaleString()}</h3></div></div></div>
                        </div>
                        {renderTable(salesReport.invoices || [], [
                            { key: 'invoiceNumber', label: 'Invoice No' },
                            { key: 'date', label: 'Date' },
                            { key: 'customer', label: 'Customer' },
                            { key: 'taxable', label: 'Taxable', format: v => `₹${v}` },
                            { key: 'gst', label: 'GST', format: v => `₹${v}` },
                            { key: 'grandTotal', label: 'Grand Total', format: v => `₹${v}` }
                        ])}
                    </div>
                </div>
            );
        }

        if (activeTab === 'purchase-report') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-success text-white d-flex justify-content-between align-items-center">
                        <h5>📉 Purchase Report</h5>
                        <div>
                            <button className="btn btn-light btn-sm me-2" onClick={() => exportToExcel(purchaseReport.purchaseOrders || [], 'Purchase_Report')}>📊 Excel</button>
                            <button className="btn btn-light btn-sm" onClick={() => exportToCSV(purchaseReport.purchaseOrders || [], 'Purchase_Report')}>📄 CSV</button>
                        </div>
                    </div>
                    <div className="card-body">
                        <div className="row g-3 mb-3">
                            <div className="col-md-3"><div className="card bg-success text-white"><div className="card-body text-center"><h6>Total POs</h6><h3>{purchaseReport.totalPOs || 0}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-info text-white"><div className="card-body text-center"><h6>Taxable Amount</h6><h3>₹{(purchaseReport.totalTaxable || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-warning text-dark"><div className="card-body text-center"><h6>Total GST</h6><h3>₹{(purchaseReport.totalGst || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-danger text-white"><div className="card-body text-center"><h6>Grand Total</h6><h3>₹{(purchaseReport.totalGrand || 0).toLocaleString()}</h3></div></div></div>
                        </div>
                        {renderTable(purchaseReport.purchaseOrders || [], [
                            { key: 'poNumber', label: 'PO No' },
                            { key: 'date', label: 'Date' },
                            { key: 'vendor', label: 'Vendor' },
                            { key: 'taxable', label: 'Taxable', format: v => `₹${v}` },
                            { key: 'gst', label: 'GST', format: v => `₹${v}` },
                            { key: 'grandTotal', label: 'Grand Total', format: v => `₹${v}` },
                            { key: 'status', label: 'Status' }
                        ])}
                    </div>
                </div>
            );
        }

        if (activeTab === 'stock-report') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-info text-white d-flex justify-content-between align-items-center">
                        <h5>📦 Stock Report</h5>
                        <div>
                            <button className="btn btn-light btn-sm me-2" onClick={() => exportToExcel(stockReport.stockData || [], 'Stock_Report')}>📊 Excel</button>
                            <button className="btn btn-light btn-sm" onClick={() => exportToCSV(stockReport.stockData || [], 'Stock_Report')}>📄 CSV</button>
                        </div>
                    </div>
                    <div className="card-body">
                        <div className="row g-3 mb-3">
                            <div className="col-md-4"><div className="card bg-primary text-white"><div className="card-body text-center"><h6>Total Items</h6><h3>{stockReport.totalItems || 0}</h3></div></div></div>
                            <div className="col-md-4"><div className="card bg-success text-white"><div className="card-body text-center"><h6>Total Value</h6><h3>₹{(stockReport.totalValue || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-4"><div className="card bg-danger text-white"><div className="card-body text-center"><h6>Low Stock Items</h6><h3>{stockReport.lowStockItems || 0}</h3></div></div></div>
                        </div>
                        <h6 className="text-danger">⚠️ Low Stock Alert</h6>
                        {renderTable(stockReport.lowStockList || [], [
                            { key: 'code', label: 'Code' },
                            { key: 'name', label: 'Product' },
                            { key: 'stock', label: 'Current Stock' },
                            { key: 'minStock', label: 'Min Stock' }
                        ])}
                        <h6 className="mt-3">📦 All Stock</h6>
                        {renderTable(stockReport.stockData || [], [
                            { key: 'code', label: 'Code' },
                            { key: 'name', label: 'Product' },
                            { key: 'stock', label: 'Stock' },
                            { key: 'minStock', label: 'Min Stock' },
                            { key: 'value', label: 'Value', format: v => `₹${v}` },
                            { key: 'status', label: 'Status' }
                        ])}
                    </div>
                </div>
            );
        }

        if (activeTab === 'gst-report') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-warning text-dark d-flex justify-content-between align-items-center">
                        <h5>🧾 GST Report</h5>
                        <div>
                            <button className="btn btn-dark btn-sm me-2" onClick={() => exportToExcel([gstReport], 'GST_Report')}>📊 Excel</button>
                            <button className="btn btn-dark btn-sm" onClick={() => exportToCSV([gstReport], 'GST_Report')}>📄 CSV</button>
                        </div>
                    </div>
                    <div className="card-body">
                        <div className="row g-3 mb-3">
                            <div className="col-md-3"><div className="card bg-primary text-white"><div className="card-body text-center"><h6>Total Invoices</h6><h3>{gstReport.totalInvoices || 0}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-danger text-white"><div className="card-body text-center"><h6>Output GST</h6><h3>₹{(gstReport.outputGST || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-success text-white"><div className="card-body text-center"><h6>Input GST</h6><h3>₹{(gstReport.inputGST || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-warning text-dark"><div className="card-body text-center"><h6>Net GST Payable</h6><h3>₹{(gstReport.netGST || 0).toLocaleString()}</h3></div></div></div>
                        </div>
                    </div>
                </div>
            );
        }

        if (activeTab === 'customer-report') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-primary text-white d-flex justify-content-between align-items-center">
                        <h5>👥 Customer Report</h5>
                        <div>
                            <button className="btn btn-light btn-sm me-2" onClick={() => exportToExcel(customerReport.customers || [], 'Customer_Report')}>📊 Excel</button>
                            <button className="btn btn-light btn-sm" onClick={() => exportToCSV(customerReport.customers || [], 'Customer_Report')}>📄 CSV</button>
                        </div>
                    </div>
                    <div className="card-body">
                        <div className="row g-3 mb-3">
                            <div className="col-md-6"><div className="card bg-primary text-white"><div className="card-body text-center"><h6>Total Customers</h6><h3>{customerReport.totalCustomers || 0}</h3></div></div></div>
                            <div className="col-md-6"><div className="card bg-success text-white"><div className="card-body text-center"><h6>Total Sales</h6><h3>₹{(customerReport.totalSales || 0).toLocaleString()}</h3></div></div></div>
                        </div>
                        {renderTable(customerReport.customers || [], [
                            { key: 'code', label: 'Code' },
                            { key: 'name', label: 'Name' },
                            { key: 'gstin', label: 'GSTIN' },
                            { key: 'phone', label: 'Phone' },
                            { key: 'city', label: 'City' },
                            { key: 'totalSales', label: 'Total Sales', format: v => `₹${v}` }
                        ])}
                    </div>
                </div>
            );
        }

        if (activeTab === 'vendor-report') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-success text-white d-flex justify-content-between align-items-center">
                        <h5>🏢 Vendor Report</h5>
                        <div>
                            <button className="btn btn-light btn-sm me-2" onClick={() => exportToExcel(vendorReport.vendors || [], 'Vendor_Report')}>📊 Excel</button>
                            <button className="btn btn-light btn-sm" onClick={() => exportToCSV(vendorReport.vendors || [], 'Vendor_Report')}>📄 CSV</button>
                        </div>
                    </div>
                    <div className="card-body">
                        <div className="row g-3 mb-3">
                            <div className="col-md-6"><div className="card bg-primary text-white"><div className="card-body text-center"><h6>Total Vendors</h6><h3>{vendorReport.totalVendors || 0}</h3></div></div></div>
                            <div className="col-md-6"><div className="card bg-danger text-white"><div className="card-body text-center"><h6>Total Purchase</h6><h3>₹{(vendorReport.totalPurchase || 0).toLocaleString()}</h3></div></div></div>
                        </div>
                        {renderTable(vendorReport.vendors || [], [
                            { key: 'code', label: 'Code' },
                            { key: 'name', label: 'Name' },
                            { key: 'gstin', label: 'GSTIN' },
                            { key: 'phone', label: 'Phone' },
                            { key: 'city', label: 'City' },
                            { key: 'totalPurchase', label: 'Total Purchase', format: v => `₹${v}` }
                        ])}
                    </div>
                </div>
            );
        }

        if (activeTab === 'trial-balance') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-dark text-white d-flex justify-content-between align-items-center">
                        <h5>⚖️ Trial Balance</h5>
                        <div>
                            <button className="btn btn-light btn-sm me-2" onClick={() => exportToExcel(trialBalance.accounts || [], 'Trial_Balance')}>📊 Excel</button>
                            <button className="btn btn-light btn-sm" onClick={() => exportToCSV(trialBalance.accounts || [], 'Trial_Balance')}>📄 CSV</button>
                        </div>
                    </div>
                    <div className="card-body">
                        {renderTable(trialBalance.accounts || [], [
                            { key: 'code', label: 'Code' },
                            { key: 'name', label: 'Account Name' },
                            { key: 'type', label: 'Type' },
                            { key: 'debit', label: 'Debit', format: v => `₹${v}` },
                            { key: 'credit', label: 'Credit', format: v => `₹${v}` },
                            { key: 'balance', label: 'Balance', format: v => `₹${v}` }
                        ])}
                        <div className="row mt-3">
                            <div className="col-md-6"><div className="card bg-primary text-white"><div className="card-body text-center"><h6>Total Debit</h6><h3>₹{(trialBalance.totalDebit || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-6"><div className="card bg-success text-white"><div className="card-body text-center"><h6>Total Credit</h6><h3>₹{(trialBalance.totalCredit || 0).toLocaleString()}</h3></div></div></div>
                        </div>
                    </div>
                </div>
            );
        }

        if (activeTab === 'profit-loss') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-danger text-white d-flex justify-content-between align-items-center">
                        <h5>💰 Profit & Loss Statement</h5>
                        <div>
                            <button className="btn btn-light btn-sm me-2" onClick={() => exportToExcel([profitLoss], 'Profit_Loss')}>📊 Excel</button>
                            <button className="btn btn-light btn-sm" onClick={() => exportToCSV([profitLoss], 'Profit_Loss')}>📄 CSV</button>
                        </div>
                    </div>
                    <div className="card-body">
                        <div className="row g-3 mb-3">
                            <div className="col-md-3"><div className="card bg-success text-white"><div className="card-body text-center"><h6>Total Revenue</h6><h3>₹{(profitLoss.totalRevenue || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-danger text-white"><div className="card-body text-center"><h6>Total Expenses</h6><h3>₹{(profitLoss.totalExpenses || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-warning text-dark"><div className="card-body text-center"><h6>Gross Profit</h6><h3>₹{(profitLoss.grossProfit || 0).toLocaleString()}</h3></div></div></div>
                            <div className="col-md-3"><div className="card bg-info text-white"><div className="card-body text-center"><h6>Net Profit</h6><h3>₹{(profitLoss.netProfit || 0).toLocaleString()}</h3></div></div></div>
                        </div>
                        <div className="text-center mt-3">
                            <h4>Profit Margin: {(profitLoss.profitMargin || 0).toFixed(2)}%</h4>
                            <div className="progress" style={{ height: '30px' }}>
                                <div className="progress-bar bg-success" style={{ width: `${Math.min(profitLoss.profitMargin || 0, 100)}%` }}>
                                    {Math.min(profitLoss.profitMargin || 0, 100).toFixed(1)}%
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // ==================== AI CHAT ====================
        if (activeTab === 'chat') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-primary text-white"><h5>💬 AI Chat Assistant</h5></div>
                    <div className="card-body" style={{ height: '500px', overflow: 'auto', display: 'flex', flexDirection: 'column' }}>
                        {chatMessages.map((msg, i) => (
                            <div key={i} className={`mb-2 ${msg.sender === 'user' ? 'text-end' : 'text-start'}`}>
                                <div className={`d-inline-block p-2 rounded ${msg.sender === 'user' ? 'bg-primary text-white' : 'bg-light text-dark'}`} style={{ maxWidth: '80%' }}>
                                    {msg.text}
                                </div>
                            </div>
                        ))}
                        {loading && <div className="text-center text-muted">Typing...</div>}
                    </div>
                    <div className="card-footer">
                        <div className="input-group">
                            <input className="form-control" placeholder="Ask me anything..." value={chatInput} onChange={(e) => setChatInput(e.target.value)} onKeyPress={(e) => e.key === 'Enter' && sendChat()} />
                            <button className="btn btn-primary" onClick={sendChat} disabled={loading}>Send</button>
                        </div>
                        <div className="mt-2">
                            {['vendors', 'products', 'sales', 'stock', 'customers', 'help'].map(q => (
                                <button key={q} className="btn btn-outline-secondary btn-sm me-1 mt-1" onClick={() => { setChatInput(q); setTimeout(sendChat, 100); }}>{q}</button>
                            ))}
                        </div>
                    </div>
                </div>
            );
        }

        // ==================== AI SEARCH ====================
        if (activeTab === 'search') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-success text-white"><h5>🔍 AI-Powered Smart Search</h5></div>
                    <div className="card-body">
                        <div className="input-group mb-3">
                            <input className="form-control" placeholder="Search products, customers, invoices..." value={searchKeyword} onChange={(e) => setSearchKeyword(e.target.value)} onKeyPress={(e) => e.key === 'Enter' && handleSmartSearch()} />
                            <button className="btn btn-success" onClick={handleSmartSearch} disabled={loading}>🔍 Search</button>
                        </div>
                        {searchResults.length > 0 ? (
                            <div className="table-responsive" style={{ maxHeight: '400px', overflow: 'auto' }}>
                                <table className="table table-striped">
                                    <thead><tr><th>Type</th><th>Details</th></tr></thead>
                                    <tbody>
                                        {searchResults.map((item, i) => (
                                            <tr key={i}>
                                                <td><span className={`badge bg-${item.type === 'Product' ? 'success' : item.type === 'Customer' ? 'primary' : 'warning'}`}>{item.type}</span></td>
                                                <td>
                                                    {item.type === 'Product' && `${item.code} - ${item.name} (₹${item.price}) Stock: ${item.stock}`}
                                                    {item.type === 'Customer' && `${item.code} - ${item.name} (${item.gstin})`}
                                                    {item.type === 'Invoice' && `${item.number} - ${item.customer} (₹${item.amount})`}
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        ) : (
                            <div className="text-center text-muted py-3">Search for products, customers, or invoices</div>
                        )}
                    </div>
                </div>
            );
        }

        // ==================== AUTO INVOICE ====================
        if (activeTab === 'auto-invoice') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-warning text-dark"><h5>⚡ AI Auto Invoice Generator</h5></div>
                    <div className="card-body">
                        <div className="row">
                            <div className="col-md-6">
                                <label className="form-label">Customer Name</label>
                                <input className="form-control" placeholder="Enter customer name" value={autoInvoiceData.customerName} onChange={(e) => setAutoInvoiceData({ ...autoInvoiceData, customerName: e.target.value })} />
                                <div className="mt-3">
                                    <h6>Items</h6>
                                    {autoInvoiceData.items.map((item, idx) => (
                                        <div key={idx} className="row g-2 mb-2">
                                            <div className="col-5"><input className="form-control form-control-sm" placeholder="Product" value={item.name} onChange={(e) => updateAutoInvoiceItem(idx, 'name', e.target.value)} /></div>
                                            <div className="col-2"><input className="form-control form-control-sm" type="number" placeholder="Qty" value={item.qty} onChange={(e) => updateAutoInvoiceItem(idx, 'qty', parseFloat(e.target.value))} /></div>
                                            <div className="col-3"><input className="form-control form-control-sm" type="number" placeholder="Rate" value={item.rate} onChange={(e) => updateAutoInvoiceItem(idx, 'rate', parseFloat(e.target.value))} /></div>
                                            <div className="col-2"><button className="btn btn-danger btn-sm" onClick={() => removeAutoInvoiceItem(idx)}>✕</button></div>
                                        </div>
                                    ))}
                                    <button className="btn btn-outline-primary btn-sm" onClick={addAutoInvoiceItem}>+ Add Item</button>
                                </div>
                                <button className="btn btn-warning mt-3 w-100" onClick={handleAutoInvoice} disabled={loading}>⚡ Generate Invoice</button>
                            </div>
                            <div className="col-md-6">
                                {autoInvoiceResult && (
                                    <div className="card bg-light">
                                        <div className="card-body">
                                            <h6>✅ Invoice Generated</h6>
                                            <p><strong>Invoice:</strong> {autoInvoiceResult.invoiceNumber}</p>
                                            <p><strong>Customer:</strong> {autoInvoiceResult.customerName}</p>
                                            <p><strong>Taxable:</strong> ₹{autoInvoiceResult.taxableAmount}</p>
                                            <p><strong>GST:</strong> ₹{autoInvoiceResult.totalGst}</p>
                                            <p><strong>Grand Total:</strong> <span className="fw-bold text-success">₹{autoInvoiceResult.grandTotal}</span></p>
                                        </div>
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // ==================== ANOMALY ====================
        if (activeTab === 'anomaly') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-danger text-white"><h5>🛡️ AI Anomaly Detection</h5></div>
                    <div className="card-body">
                        <button className="btn btn-danger mb-3" onClick={handleAnomalyDetection} disabled={loading}>🔍 Scan for Anomalies</button>
                        {anomalies.length > 0 ? (
                            <div className="table-responsive">
                                <table className="table table-striped">
                                    <thead><tr><th>Type</th><th>Details</th><th>Risk Level</th></tr></thead>
                                    <tbody>
                                        {anomalies.map((a, i) => (
                                            <tr key={i}>
                                                <td><span className="badge bg-danger">{a.type}</span></td>
                                                <td>{a.invoice ? `Invoice: ${a.invoice} (₹${a.amount})` : a.product ? `Product: ${a.product} (Stock: ${a.stock})` : 'N/A'}</td>
                                                <td><span className={`badge bg-${a.risk === 'HIGH' ? 'danger' : 'warning'}`}>{a.risk}</span></td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        ) : (
                            <div className="text-center text-muted py-3">Click scan to detect anomalies</div>
                        )}
                    </div>
                </div>
            );
        }

        // ==================== PREDICT ====================
        if (activeTab === 'predict') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-success text-white"><h5>📊 AI Predictive Analytics</h5></div>
                    <div className="card-body">
                        <button className="btn btn-success mb-3" onClick={handlePredict} disabled={loading}>📈 Predict Sales (7 Days)</button>
                        {prediction && (
                            <div className="row">
                                <div className="col-md-6"><div className="card bg-primary text-white"><div className="card-body text-center"><h6>Average Daily Sales</h6><h3>₹{prediction.avgDailySales?.toFixed(2) || 0}</h3></div></div></div>
                                <div className="col-md-6"><div className="card bg-success text-white"><div className="card-body text-center"><h6>Predicted Sales (7 Days)</h6><h3>₹{prediction.predictedSales?.toFixed(2) || 0}</h3><small>Confidence: {(prediction.confidence || 0) * 100}%</small></div></div></div>
                            </div>
                        )}
                    </div>
                </div>
            );
        }

        // ==================== QR SCANNER ====================
        if (activeTab === 'qr') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-info text-white"><h5>📱 QR Code Scanner</h5></div>
                    <div className="card-body">
                        <div className="input-group mb-3">
                            <input className="form-control" placeholder="Enter QR code data (PROD-XXX or INV-XXX)" value={qrData} onChange={(e) => setQrData(e.target.value)} onKeyPress={(e) => e.key === 'Enter' && handleQRScan()} />
                            <button className="btn btn-info" onClick={handleQRScan} disabled={loading}>📱 Scan</button>
                        </div>
                        {qrResult && (
                            <div className="card bg-light">
                                <div className="card-body">
                                    <h6>📋 Scan Result</h6>
                                    <p><strong>Type:</strong> <span className={`badge bg-${qrResult.type === 'PRODUCT' ? 'success' : qrResult.type === 'INVOICE' ? 'warning' : 'secondary'}`}>{qrResult.type || 'UNKNOWN'}</span></p>
                                    <p><strong>Message:</strong> {qrResult.message}</p>
                                </div>
                            </div>
                        )}
                    </div>
                </div>
            );
        }

        // ==================== EXTRACT ====================
        if (activeTab === 'extract') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-secondary text-white"><h5>📄 AI Smart Data Extraction</h5></div>
                    <div className="card-body">
                        <div className="mb-3">
                            <label className="form-label">Paste Invoice/Text Data</label>
                            <textarea className="form-control" rows="5" placeholder="Paste invoice text here..." value={extractText} onChange={(e) => setExtractText(e.target.value)}></textarea>
                            <button className="btn btn-secondary mt-2" onClick={handleExtract} disabled={loading}>📄 Extract Data</button>
                        </div>
                        {extractResult && (
                            <div className="card bg-light">
                                <div className="card-body">
                                    <h6>📋 Extracted Data</h6>
                                    <p><strong>Customer:</strong> {extractResult.customerName || 'N/A'}</p>
                                    <p><strong>GSTIN:</strong> {extractResult.gstin || 'N/A'}</p>
                                    <p><strong>Amount:</strong> {extractResult.amount ? `₹${extractResult.amount}` : 'N/A'}</p>
                                    <p><strong>Confidence:</strong> {(extractResult.confidence || 0) * 100}%</p>
                                </div>
                            </div>
                        )}
                    </div>
                </div>
            );
        }

        // ==================== AI AGENTS ====================
        if (activeTab === 'agents') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-primary text-white"><h5>🤖 AI Agents - Auto Approval & Tasks</h5></div>
                    <div className="card-body">
                        <button className="btn btn-primary mb-3" onClick={handleRunAgents} disabled={loading}>▶️ Run AI Agents</button>
                        {agentResults.length > 0 ? (
                            <div className="row">
                                {agentResults.map((agent, i) => (
                                    <div key={i} className="col-md-6 mb-2">
                                        <div className={`card ${agent.status === 'SUCCESS' ? 'border-success' : 'border-danger'}`}>
                                            <div className="card-body">
                                                <h6>🤖 {agent.agent}</h6>
                                                <p className="mb-1">Status: <span className={`badge bg-${agent.status === 'SUCCESS' ? 'success' : 'danger'}`}>{agent.status}</span></p>
                                                {agent.approved !== undefined && <p className="mb-0">✅ Approved: {agent.approved} invoices</p>}
                                                {agent.lowStockCount !== undefined && <p className="mb-0">📦 Low Stock: {agent.lowStockCount} items</p>}
                                            </div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        ) : (
                            <div className="text-center text-muted py-3">Run agents to see results</div>
                        )}
                    </div>
                </div>
            );
        }

        // ==================== SETTINGS ====================
        if (activeTab === 'settings') {
            return (
                <div className="card shadow-sm">
                    <div className="card-header bg-dark text-white"><h5>⚙️ Settings</h5></div>
                    <div className="card-body">
                        <div className="text-center py-5">
                            <h4>⚙️ Application Settings</h4>
                            <p className="text-muted">Configure your application preferences</p>
                            <div className="row mt-4">
                                <div className="col-md-6">
                                    <div className="card bg-light">
                                        <div className="card-body">
                                            <h6>🌓 Theme</h6>
                                            <button className="btn btn-primary" onClick={() => setIsDarkMode(!isDarkMode)}>
                                                {isDarkMode ? '☀️ Light Mode' : '🌙 Dark Mode'}
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="card bg-light">
                                        <div className="card-body">
                                            <h6>👤 Profile</h6>
                                            <p><strong>Name:</strong> {CREATOR.name}</p>
                                            <p><strong>Email:</strong> {CREATOR.email}</p>
                                            <p><strong>Phone:</strong> {CREATOR.phone}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="row mt-3">
                                <div className="col-md-12">
                                    <div className="card bg-light">
                                        <div className="card-body">
                                            <h6>📊 System Info</h6>
                                            <p><strong>Version:</strong> 1.0.0</p>
                                            <p><strong>Created By:</strong> {CREATOR.name}</p>
                                            <p><strong>College:</strong> {CREATOR.college}</p>
                                            <p><strong>University:</strong> {CREATOR.university}</p>
                                            <p><strong>Location:</strong> {CREATOR.location}</p>
                                            <p><strong>© 2026 BHARAT ERP | Made in India 🇮🇳</strong></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        // Default fallback
        return (
            <div className="card shadow-sm">
                <div className="card-header bg-primary text-white"><h5>📋 {activeTab.replace('-', ' ').toUpperCase()}</h5></div>
                <div className="card-body">
                    <div className="text-center py-5">
                        <h4>🚀 Module Ready</h4>
                        <p className="text-muted">Phase 1-10 Complete ✅</p>
                        <p className="text-success">All modules with full CRUD operations</p>
                        <p className="text-warning">👨‍💻 Created by: {CREATOR.name}</p>
                    </div>
                </div>
            </div>
        );
    };

    // ==================== MAIN RETURN ====================
    return (
        <div style={{ minHeight: '100vh', backgroundColor: '#f4f6f9' }}>
            <Navbar />
            <LeftSidebar />
            <RightSidebar />
            
            <main style={{ 
                marginLeft: leftOpen ? '220px' : '60px',
                marginRight: rightOpen ? '220px' : '60px',
                paddingTop: '60px',
                minHeight: '100vh',
                transition: 'all 0.3s ease-in-out'
            }}>
                <div className="container-fluid p-4">
                    {message.text && (
                        <div className={`alert alert-${message.type} alert-dismissible`}>
                            {message.text}
                            <button className="btn-close" onClick={() => setMessage({ text: '', type: '' })}></button>
                        </div>
                    )}
                    {renderContent()}
                </div>
                <Footer />
            </main>

            {/* ===== MODAL ===== */}
            {showModal && (
                <div className="modal show d-block" style={{ backgroundColor: 'rgba(0,0,0,0.5)' }}>
                    <div className="modal-dialog modal-lg modal-dialog-scrollable">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5>{modalType === 'invoice' ? '📄 Create Invoice' : '📋 Create Purchase Order'}</h5>
                                <button className="btn-close" onClick={() => setShowModal(false)}></button>
                            </div>
                            <div className="modal-body">
                                {modalType === 'invoice' ? (
                                    <form onSubmit={handleInvoiceSubmit}>
                                        <div className="row g-2 mb-3">
                                            <div className="col-6">
                                                <label className="form-label">Customer</label>
                                                <select className="form-select" value={invoiceForm.customerId} onChange={(e) => {
                                                    const cust = customers.find(c => c.id === parseInt(e.target.value));
                                                    if (cust) {
                                                        setInvoiceForm({ customerId: cust.id, customerName: cust.name, customerGstin: cust.gstin || '', customerAddress: cust.address || '' });
                                                    }
                                                }}>
                                                    <option value="">Select Customer</option>
                                                    {customers.map(c => <option key={c.id} value={c.id}>{c.code} - {c.name}</option>)}
                                                </select>
                                            </div>
                                            <div className="col-6"><label className="form-label">GSTIN</label><input className="form-control" value={invoiceForm.customerGstin} readOnly /></div>
                                            <div className="col-12"><label className="form-label">Address</label><input className="form-control" value={invoiceForm.customerAddress} readOnly /></div>
                                        </div>
                                        <h6>Items</h6>
                                        <div className="table-responsive">
                                            <table className="table table-sm">
                                                <thead><tr><th>Product</th><th>Qty</th><th>Rate</th><th>GST</th><th>Amount</th><th>Total</th><th>Action</th></tr></thead>
                                                <tbody>
                                                    {invoiceItems.map((item, idx) => (
                                                        <tr key={idx}>
                                                            <td>{item.name}</td>
                                                            <td>{item.qty}</td>
                                                            <td>₹{item.rate}</td>
                                                            <td>{item.gstRate}%</td>
                                                            <td>₹{item.amount}</td>
                                                            <td>₹{item.total}</td>
                                                            <td><button type="button" className="btn btn-danger btn-sm" onClick={() => removeInvoiceItem(idx)}>✕</button></td>
                                                        </tr>
                                                    ))}
                                                </tbody>
                                                <tfoot><tr><td colSpan="4" className="text-end"><strong>Grand Total:</strong></td>
                                                    <td colSpan="3"><strong>₹{invoiceItems.reduce((sum, i) => sum + i.total, 0).toFixed(2)}</strong></td></tr></tfoot>
                                            </table>
                                        </div>
                                        <button type="submit" className="btn btn-primary w-100" disabled={loading}>{loading ? 'Saving...' : '💾 Save Invoice'}</button>
                                    </form>
                                ) : (
                                    <form onSubmit={handlePOSubmit}>
                                        <div className="row g-2 mb-3">
                                            <div className="col-6">
                                                <label className="form-label">Vendor</label>
                                                <select className="form-select" value={poForm.vendorId} onChange={(e) => {
                                                    const v = vendors.find(v => v.id === parseInt(e.target.value));
                                                    if (v) {
                                                        setPoForm({ vendorId: v.id, vendorName: v.name, vendorGstin: v.gstin || '', deliveryDate: poForm.deliveryDate });
                                                    }
                                                }}>
                                                    <option value="">Select Vendor</option>
                                                    {vendors.map(v => <option key={v.id} value={v.id}>{v.code} - {v.name}</option>)}
                                                </select>
                                            </div>
                                            <div className="col-6"><label className="form-label">Vendor GSTIN</label><input className="form-control" value={poForm.vendorGstin} readOnly /></div>
                                            <div className="col-6"><label className="form-label">Delivery Date</label><input className="form-control" type="date" value={poForm.deliveryDate} onChange={(e) => setPoForm({...poForm, deliveryDate: e.target.value})} /></div>
                                        </div>
                                        <h6>Items</h6>
                                        <div className="table-responsive">
                                            <table className="table table-sm">
                                                <thead><tr><th>Product</th><th>Qty</th><th>Rate</th><th>GST</th><th>Amount</th><th>Total</th><th>Action</th></tr></thead>
                                                <tbody>
                                                    {poItems.map((item, idx) => (
                                                        <tr key={idx}>
                                                            <td>{item.name}</td>
                                                            <td>{item.qty}</td>
                                                            <td>₹{item.rate}</td>
                                                            <td>{item.gstRate}%</td>
                                                            <td>₹{item.amount}</td>
                                                            <td>₹{item.total}</td>
                                                            <td><button type="button" className="btn btn-danger btn-sm" onClick={() => removePOItem(idx)}>✕</button></td>
                                                        </tr>
                                                    ))}
                                                </tbody>
                                                <tfoot><tr><td colSpan="4" className="text-end"><strong>Grand Total:</strong></td>
                                                    <td colSpan="3"><strong>₹{poItems.reduce((sum, i) => sum + i.total, 0).toFixed(2)}</strong></td></tr></tfoot>
                                            </table>
                                        </div>
                                        <button type="submit" className="btn btn-success w-100" disabled={loading}>{loading ? 'Saving...' : '💾 Save Purchase Order'}</button>
                                    </form>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            )}

            <style>{`
                .width-220 { width: 220px; }
                .width-60 { width: 60px; }
                .hover-bg-primary-20:hover { background-color: rgba(13, 110, 253, 0.2); }
                .transition-all { transition: all 0.3s ease-in-out; }
                .btn:focus { box-shadow: none; }
            `}</style>
        </div>
    );
}

export default App;
