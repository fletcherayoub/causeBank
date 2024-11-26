// components/Auth/SignIn.jsx
import React from 'react';
import { motion } from 'framer-motion';
import { Link } from 'react-router-dom';

const signin = () => {
  return (
    <div className="min-h-screen bg-white flex flex-col md:flex-row">
      {/* Left Section - Sign In Form */}
      <div className="w-full md:w-1/2 p-4 sm:p-6 md:p-12 lg:p-16">
        <div className="max-w-md mx-auto">
          <div className="mb-6 md:mb-8">
            <h1 className="text-2xl sm:text-3xl md:text-4xl font-bold text-[#333333] mb-2">
              Welcome back to CauseBank
            </h1>
            <p className="text-gray-600 text-sm sm:text-base">
              Sign in to continue your journey of making a difference
            </p>
          </div>

          {/* Sign In Form */}
          <form className="space-y-4 sm:space-y-6">
            <div>
              <label className="block text-sm font-medium text-[#333333] mb-1.5 sm:mb-2">
                Email Address
              </label>
              <input
                type="email"
                className="w-full px-3 sm:px-4 py-2.5 sm:py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-[#3767a6] focus:border-transparent outline-none transition-all text-sm sm:text-base"
                placeholder="Enter your email"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-[#333333] mb-1.5 sm:mb-2">
                Password
              </label>
              <input
                type="password"
                className="w-full px-3 sm:px-4 py-2.5 sm:py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-[#3767a6] focus:border-transparent outline-none transition-all text-sm sm:text-base"
                placeholder="Enter your password"
              />
            </div>

            <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3 sm:gap-0">
              <div className="flex items-center">
                <input
                  type="checkbox"
                  className="h-4 w-4 text-[#3767a6] focus:ring-[#3767a6] border-gray-300 rounded"
                />
                <label className="ml-2 block text-sm text-gray-600">
                  Remember me
                </label>
              </div>
              <a
                href="#"
                className="text-sm font-medium text-[#3767a6] hover:underline"
              >
                Forgot password?
              </a>
            </div>

            <motion.button
              whileHover={{ scale: 1.02 }}
              whileTap={{ scale: 0.98 }}
              className="w-full bg-gradient-to-r from-[#96b3d9] to-[#3767a6] text-white py-2.5 sm:py-3 rounded-lg font-medium hover:opacity-90 transition-all duration-200 text-sm sm:text-base shadow-md"
              type="submit"
            >
              Sign In
            </motion.button>
          </form>

          <div className="mt-6 sm:mt-8 text-center">
            <span className="text-gray-600 text-sm sm:text-base">Don't have an account? </span>
            <Link to="/signupType" className="text-[#3767a6] font-medium hover:underline text-sm sm:text-base">
              Sign up
            </Link>
          </div>
        </div>
      </div>

      {/* Right Section - Feature Highlights */}
      <div className="hidden md:flex w-1/2 bg-gradient-to-br from-[#96b3d9] to-[#3767a6] p-8 lg:p-12 items-center justify-center">
        <div className="max-w-md text-white">
          <h2 className="text-2xl lg:text-3xl font-bold mb-4 lg:mb-6">Join the Movement</h2>
          <p className="mb-6 lg:mb-8 text-sm lg:text-base">
            CauseBank connects passionate individuals with meaningful causes.
            Together, we can create lasting change in our communities.
          </p>

          <div className="space-y-3 lg:space-y-4">
            {[
              'Secure and transparent donations',
              'Real-time impact tracking',
              'Community of changemakers',
              '24/7 dedicated support'
            ].map((feature, index) => (
              <div key={index} className="flex items-center gap-2 lg:gap-3">
                <svg
                  className="w-4 h-4 lg:w-5 lg:h-5 text-white flex-shrink-0"
                  fill="none"
                  stroke="currentColor"
                  viewBox="0 0 24 24"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    d="M5 13l4 4L19 7"
                  />
                </svg>
                <span className="text-sm lg:text-base">{feature}</span>
              </div>
            ))}
          </div>

          <div className="mt-8 lg:mt-12">
            <div className="flex items-center gap-3 lg:gap-4">
              <div className="flex -space-x-2">
                {[1, 2, 3].map((_, index) => (
                  <div
                    key={index}
                    className="w-7 h-7 lg:w-8 lg:h-8 rounded-full bg-white/20 border-2 border-white"
                  />
                ))}
              </div>
              <p className="text-xs lg:text-sm">
                Join thousands of donors making a difference
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default signin;
