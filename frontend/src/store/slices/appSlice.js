import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  theme: 'light',
  sidebarOpen: true,
  notifications: [],
}

const appSlice = createSlice({
  name: 'app',
  initialState,
  reducers: {
    toggleTheme: (state) => {
      state.theme = state.theme === 'light' ? 'dark' : 'light'
    },
    toggleSidebar: (state) => {
      state.sidebarOpen = !state.sidebarOpen
    },
    addNotification: (state, action) => {
      state.notifications.push(action.payload)
    },
    clearNotifications: (state) => {
      state.notifications = []
    },
  },
})

export const { toggleTheme, toggleSidebar, addNotification, clearNotifications } = appSlice.actions
export default appSlice.reducer
