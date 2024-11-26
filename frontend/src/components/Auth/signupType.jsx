// SignupType.jsx
import React from 'react'
import { Link } from 'react-router-dom'

const SignupType = () => {
  return (
    <div className="flex flex-col lg:flex-row min-h-screen">
      {/* Left Section */}
      <div className="w-full lg:w-1/2 p-4 md:p-8 bg-white">
        <div className="mb-6 md:mb-8">
          <h2 className="text-xl md:text-2xl font-semibold mb-2 text-[#333333]">Make a Difference Today</h2>
          <p className="text-sm md:text-base text-gray-600">Choose how you'd like to contribute to positive change</p>
        </div>
        
        {/* Non Profit Option */}
        <div className="mb-3 md:mb-4 p-4 md:p-6 border rounded-lg hover:border-[#3767a6] hover:bg-[#96b3d9]/10 cursor-pointer transition-all duration-300">
          <div className="flex items-center gap-2 md:gap-3">
            <svg className="w-5 h-5 md:w-6 md:h-6 text-[#3767a6] flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
            </svg>
            <div>
              <h3 className="font-semibold text-[#3767a6] text-sm md:text-base">Non Profit Organization</h3>
              <p className="text-gray-600 text-xs md:text-sm">Create an impact-driven fundraising platform for your non-profit</p>
            </div>
          </div>
        </div>

        {/* For Profit Option */}
        <div className="mb-3 md:mb-4 p-4 md:p-6 border rounded-lg hover:border-[#3767a6] hover:bg-[#96b3d9]/10 cursor-pointer transition-all duration-300">
          <div className="flex items-center gap-2 md:gap-3">
            <svg className="w-5 h-5 md:w-6 md:h-6 text-[#3767a6] flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
            </svg>
            <div>
              <h3 className="font-semibold text-[#3767a6] text-sm md:text-base">Social Enterprise</h3>
              <p className="text-gray-600 text-xs md:text-sm">Set up fundraising for your mission-driven business or organization</p>
            </div>
          </div>
        </div>

        {/* Individual Option */}
        <div className="mb-3 md:mb-4 p-4 md:p-6 border rounded-lg hover:border-[#3767a6] hover:bg-[#96b3d9]/10 cursor-pointer transition-all duration-300">
          <div className="flex items-center gap-2 md:gap-3">
            <svg className="w-5 h-5 md:w-6 md:h-6 text-[#3767a6] flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
            </svg>
            <div>
              <h3 className="font-semibold text-[#3767a6] text-sm md:text-base">Individual Fundraiser</h3>
              <p className="text-gray-600 text-xs md:text-sm">Start a personal fundraising campaign for yourself or others</p>
            </div>
          </div>
        </div>

        {/* Existing Account Access */}
        <div className="mb-3 md:mb-4 p-4 md:p-6 bg-gradient-to-r from-[#96b3d9]/10 to-amber-50 rounded-lg">
          <h3 className="font-semibold text-[#333333] text-sm md:text-base">Access Existing Account</h3>
          <Link to="/contact" className="text-amber-600 hover:text-amber-700 hover:underline text-xs md:text-sm">
            Contact our support team →
          </Link>
        </div>

        {/* Sign In Link */}
        <div className="mt-4 md:mt-6 text-sm md:text-base">
          <span className="text-gray-600">Already making a difference? </span>
          <Link to="/signin" className="text-[#3767a6] hover:text-[#96b3d9] font-medium hover:underline text-sm sm:text-base">
            Sign in to your account
          </Link>
        </div>
      </div>

      {/* Right Section */}
      <div className="hidden md:flex w-1/2 bg-gradient-to-br from-[#96b3d9] to-[#3767a6] p-8 lg:p-12 items-center justify-center text-white">
        <div className="max-w-md mx-auto">
          <h1 className="text-2xl md:text-4xl font-bold mb-4 md:mb-6">Empower Change Through Giving</h1>
          
          <p className="mb-6 md:mb-8 text-white/90 text-sm md:text-base">
            Join a community dedicated to making the world better. Our platform provides the tools and support you need to create meaningful impact.
          </p>

          <div className="space-y-4 md:space-y-6">
            {/* Feature Items */}
            {[
              'Secure & Transparent Donations',
              'Powerful Campaign Management',
              'Dedicated Support & Resources',
              'Real-time Analytics & Insights'
            ].map((feature, index) => (
              <div key={index} className="flex items-center gap-2 md:gap-3">
                <div className="p-1.5 md:p-2 bg-amber-500/20 rounded-full">
                  <svg className="w-4 h-4 md:w-5 md:h-5 text-amber-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M5 13l4 4L19 7" />
                  </svg>
                </div>
                <span className="text-white/90 text-sm md:text-base">{feature}</span>
              </div>
            ))}
          </div>

          <button className="w-full md:w-auto mt-6 md:mt-8 bg-gradient-to-r from-amber-500 to-amber-600 text-white px-6 md:px-8 py-2.5 md:py-3 rounded-full font-semibold flex items-center justify-center md:justify-start gap-2 hover:from-amber-600 hover:to-amber-700 transition-all duration-300 shadow-lg text-sm md:text-base">
            Start Your Journey
            <svg className="w-4 h-4 md:w-5 md:h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M17 8l4 4m0 0l-4 4m4-4H3" />
            </svg>
          </button>

          <div className="mt-4 md:mt-6 text-center text-white/80 text-xs md:text-sm">
            Join over 10,000+ organizations making a difference
          </div>
        </div>
      </div>
    </div>
  )
}

export default SignupType