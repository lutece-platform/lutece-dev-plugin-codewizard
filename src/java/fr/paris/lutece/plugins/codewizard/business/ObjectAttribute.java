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
 * This class provide the ObjectAttribute object
 */
public class ObjectAttribute
{
    private static final String DEFAULT_INPUT_TYPE = "text";

    private String _strColumnName;
    private String _strJavaType;
    private String _strName;
    private String _strVariableName;
    private String _strInputType = DEFAULT_INPUT_TYPE;
    private boolean _bDaoType;
    private String _strTestInitValue1;
    private String _strTestInitValue2;

    /**
     * Sets the Name
     *
     * @param strName The Name
     */
    public void setName( String strName )
    {
        _strName = strName;
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
     * Returns the VariableName
     *
     * @return The VariableName
     */
    public String getVariableName()
    {
        return _strVariableName;
    }

    /**
     * Sets the VariableName
     *
     * @param strVariableName The VariableName
     */
    public void setVariableName( String strVariableName )
    {
        _strVariableName = strVariableName;
    }

    /**
     * Returns the DaoType
     *
     * @return The DaoType
     */
    public boolean isDaoType()
    {
        return _bDaoType;
    }

    /**
     * Sets the DaoType
     *
     * @param bDaoType The DaoType
     */
    public void setDaoType( boolean bDaoType )
    {
        _bDaoType = bDaoType;
    }

    /**
     * Sets the column name
     *
     * @param strColumnName The column name
     */
    public void setColumnName( String strColumnName )
    {
        _strColumnName = strColumnName;
    }

    /**
     * Returns the ColumnName
     *
     * @return The ColumnName
     */
    public String getColumnName()
    {
        return _strColumnName;
    }

    /**
     * Sets the Type
     *
     * @param strType The Type
     */
    public void setType( String strType )
    {
        _strJavaType = strType;
    }

    /**
     * Returns the Type
     *
     * @return The Type
     */
    public String getType()
    {
        return _strJavaType;
    }

    /**
     * Sets the InputType
     *
     * @param strInputType The InputType
     */
    public void setInputType( String strInputType )
    {
        _strInputType = strInputType;
    }

    /**
     * Returns the InputType
     *
     * @return The InputType
     */
    public String getInputType()
    {
        return _strInputType;
    }

    /**
     * Returns the TestInitValue1
     *
     * @return The TestInitValue1
     */
    public String getTestInitValue1()
    {
        return _strTestInitValue1;
    }

    /**
     * Sets the TestInitValue1
     *
     * @param strTestInitValue1 The TestInitValue1
     */
    public void setTestInitValue1( String strTestInitValue1 )
    {
        _strTestInitValue1 = strTestInitValue1;
    }

    /**
     * Returns the TestInitValue2
     *
     * @return The TestInitValue2
     */
    public String getTestInitValue2()
    {
        return _strTestInitValue2;
    }

    /**
     * Sets the TestInitValue2
     *
     * @param strTestInitValue2 The TestInitValue2
     */
    public void setTestInitValue2( String strTestInitValue2 )
    {
        _strTestInitValue2 = strTestInitValue2;
    }
}
