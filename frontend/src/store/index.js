import { configureStore } from '@reduxjs/toolkit'
import authReducer from './slices/authSlice'
import appReducer from './slices/appSlice'

export const store = configureStore({
  reducer: {
    auth: authReducer,
    app: appReducer,
  },
})

// For TypeScript support, use JSDoc comments instead
/**
 * @typedef {ReturnType<typeof store.getState>} RootState
 * @typedef {typeof store.dispatch} AppDispatch
 */
