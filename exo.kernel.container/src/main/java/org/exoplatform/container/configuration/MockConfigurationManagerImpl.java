/*
 * Copyright (C) 2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.container.configuration;

import java.net.URL;

import javax.servlet.ServletContext;

/**
 * Jul 19, 2004
 * 
 * @author: Tuan Nguyen
 * @email: tuan08@users.sourceforge.net
 * @version: $Id: MockConfigurationManagerImpl.java 5799 2006-05-28 17:55:42Z
 *           geaz $
 */
public class MockConfigurationManagerImpl extends ConfigurationManagerImpl
{
   private String confDir_;

   public MockConfigurationManagerImpl(ServletContext context) throws Exception
   {
      super(context);
      confDir_ = System.getProperty("mock.portal.dir") + "/WEB-INF";
   }

   public URL getURL(String uri) throws Exception
   {
      if (uri.startsWith("jar:"))
      {
         String path = removePrefix("jar:/", uri);
         ClassLoader cl = Thread.currentThread().getContextClassLoader();
         return cl.getResource(path);
      }
      else if (uri.startsWith("classpath:"))
      {
         String path = removePrefix("classpath:/", uri);
         ClassLoader cl = Thread.currentThread().getContextClassLoader();
         return cl.getResource(path);
      }
      else if (uri.startsWith("war:"))
      {
         String path = removePrefix("war:", uri);
         URL url = new URL("file:" + confDir_ + path);
         return url;
      }
      else if (uri.startsWith("file:"))
      {
         return new URL(uri);
      }
      return null;
   }
}