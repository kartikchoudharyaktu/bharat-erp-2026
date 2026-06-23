import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  user: null,
  token: localStorage.getItem('token'),
  isAuthenticated: false,
  loading: false,
  error: null,
}

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setLoading: (state, action) => {
      state.loading = action.payload
    },
    setUser: (state, action) => {
      state.user = action.payload
      state.isAuthenticated = true
      state.loading = false
    },
    setToken: (state, action) => {
      state.token = action.payload
      localStorage.setItem('token', action.payload)
    },
    logout: (state) => {
      state.user = null
      state.token = null
      state.isAuthenticated = false
      localStorage.removeItem('token')
    },
    setError: (state, action) => {
      state.error = action.payload
      state.loading = false
    },
  },
})

export const { setLoading, setUser, setToken, logout, setError } = authSlice.actions
export default authSlice.reducer
