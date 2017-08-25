/*
 * Copyright (c) 2002-2017, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.codewizard.business;

/**
 * JavaType
 */
public class JavaType
{

    // Variables declarations
    private String _strName;
    private String _strPrefix;
    private boolean _bIsDaoType;
    private String _strImport;
    private String _strTestInitValueFormat;

    /**
     * Constructor
     *
     * @param strName The name
     * @param strPrefix The prefix
     * @param bDaoType if it's a dao attribute
     * @param strImport The java type import if necessary
     * @param strTestInitValueFormat test init value format
     */
    public JavaType( String strName, String strPrefix, boolean bDaoType, String strImport, String strTestInitValueFormat )
    {
        _strName = strName;
        _strPrefix = strPrefix;
        _bIsDaoType = bDaoType;
        _strImport = strImport;
        _strTestInitValueFormat = strTestInitValueFormat;
    }

    /**
     * Returns the Name
     *
     * @return The Name
     */
    public String getName()
    {
        return _strName;
    }

    /**
     * Returns the Prefix
     *
     * @return The Prefix
     */
    public String getPrefix()
    {
        return _strPrefix;
    }

    /**
     * Return true if the type is DAOUtil compatible
     *
     * @return true if the type is DAOUtil compatible
     */
    public boolean isDaoType()
    {
        return _bIsDaoType;
    }

    /**
     * Returns the Import
     *
     * @return The Import
     */
    public String getImport()
    {
        return _strImport;
    }

    /**
     * Returns the TestInitValueFormat
     *
     * @return The TestInitValueFormat
     */
    public String getTestInitValueFormat()
    {
        return _strTestInitValueFormat;
    }

}
