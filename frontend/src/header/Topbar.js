import React, { useState, useEffect, useRef } from 'react';
import { Link } from 'react-router-dom';
import './css/Topbar.scss';

import { PiCaretDownBold } from "react-icons/pi";
import { MenuItems } from './MenuItems';

export default function Topbar({ user }) {
    const dropdownRef = useRef(null);
    const mobileDropdownRef = useRef(null);
    const [dropdown, setDropdown] = useState(false);
    const [mobileDropdown, setMobileDropdown] = useState(false);

    const mobileDisplayedItem = MenuItems[user ? 1 : 0]

    const handleClickOutside = (e) => {
        if (dropdown && dropdownRef.current && !dropdownRef.current.contains(e.target)
        ) {
            setDropdown(false);
        }
        if (mobileDropdown && mobileDropdownRef.current && !mobileDropdownRef.current.contains(e.target)) {
            setMobileDropdown(false);
        }
    };

    useEffect(() => {
        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, [dropdown, mobileDropdown]);

    return (
        <nav className='navbar'>
                <Link to='/' className='navbar-title' >
                    Melodicle
                </Link>
                <ul className='nav-menu desktop-only'>
                    <li className='nav-item'>
                        <Link to='/' className='nav-links' >
                            Home
                        </Link>
                    </li>
                    {user && (
                        <>
                            <li className='nav-item'>
                                <Link
                                    to='/submissions'
                                    className='nav-links'
                                >
                                    Submissions
                                </Link>
                            </li>
                            <li className='nav-item'>
                                <Link
                                    to='/students'
                                    className='nav-links'
                                >
                                    Students
                                </Link>
                            </li>
                            <li className='nav-item'>
                                <Link
                                    to='/checksheet'
                                    className='nav-links'
                                >
                                    Checksheet
                                </Link>
                            </li>
                            {user?.role === 'admin' && (
                                <li className='nav-item'>
                                    <Link
                                        to='/admin'
                                        className='nav-links'
                                    >
                                        Admin
                                    </Link>
                                </li>
                            )}
                        </>
                    )}
                    <li
                        className='nav-item'
                        onClick={() => setDropdown(!dropdown)}
                        ref={dropdownRef}
                    >
                        <div className='nav-links'>
                            Account <i className='fas fa-caret-down' />
                        </div>
                        {dropdown &&
                            <ul className='nav-dropdown-list desktop-only'>
                                <li className='nav-item'>
                                    <a
                                        className='nav-links'
                                        href={mobileDisplayedItem.path}
                                    >
                                        {mobileDisplayedItem.title}
                                    </a>
                                </li>
                            </ul>
                        }
                    </li>
                </ul>
                <span
                    className={`mobile-dropdown-icon${mobileDropdown ? ' expanded' : ''}`}
                    title='Navigation menu'
                    ref={mobileDropdownRef}
                >
                    <PiCaretDownBold
                        onClick={() => setMobileDropdown(!mobileDropdown)}
                    />
                    {mobileDropdown && <ul className='nav-dropdown-list mobile-only'>
                        <li className='nav-item'>
                            <Link to='/' className='nav-links'>
                                Home
                            </Link>
                        </li>
                        {user && (
                            <>
                                <li className='nav-item'>
                                    <Link
                                        to='/submissions'
                                        className='nav-links'
                                    >
                                        Submissions
                                    </Link>
                                </li>
                                <li className='nav-item'>
                                    <Link
                                        to='/students'
                                        className='nav-links'
                                    >
                                        Students
                                    </Link>
                                </li>
                                <li className='nav-item'>
                                    <Link
                                        to='/checksheet'
                                        className='nav-links'
                                    >
                                        Checksheet
                                    </Link>
                                </li>
                                {user?.role === 'admin' && (
                                    <li className='nav-item'>
                                        <Link
                                            to='/admin'
                                            className='nav-links'
                                        >
                                            Admin
                                        </Link>
                                    </li>
                                )}
                            </>
                        )}
                        <li className='nav-item'>
                            <a
                                className='nav-links'
                                href={mobileDisplayedItem.path}
                            >
                                {mobileDisplayedItem.title}
                            </a>
                        </li>
                    </ul>}
                </span>
            </nav >
    );
}